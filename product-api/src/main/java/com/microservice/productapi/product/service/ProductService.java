package com.microservice.productapi.product.service;

import com.microservice.productapi.category.service.CategoryService;
import com.microservice.productapi.product.dto.ProductRequest;
import com.microservice.productapi.product.dto.ProductResponse;
import com.microservice.productapi.product.dto.ProductStockDTO;
import com.microservice.productapi.product.model.Product;
import com.microservice.productapi.product.repository.ProductRepository;
import com.microservice.productapi.sales.dto.SalesConfirmationDTO;
import com.microservice.productapi.sales.enums.SalesStatus;
import com.microservice.productapi.sales.rabbitmq.SalesConfirmationSender;
import com.microservice.productapi.supplier.service.SupplierService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    @Autowired
    private SalesConfirmationSender salesConfirmationSender;

    public ProductResponse save (ProductRequest request){
        var product = new Product().builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .category(categoryService.findById(request.getCategoryId()))
                .supplier(supplierService.findById(request.getSupplierId()))
                .build();
        product = repository.save(product);
        return ProductResponse.of(product);
    }

    public Product findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não encontrado id: " + id));
    }

    public void updateProductStock(ProductStockDTO productStockDTO){
        try {
            validateStockUpdateData(productStockDTO);

            productStockDTO
                    .getProducts()
                    .forEach(s -> {
                        var prod = findById(s.getProductId());
                        if(s.getQuantity() > prod.getQuantity()) {
                            throw new IllegalArgumentException(String.format("O produto %s não tem estoque", prod.getId()));
                        }
                        prod.updateStock(s.getQuantity());
                        repository.save(prod);
                        var validMessage= new SalesConfirmationDTO(productStockDTO.getSalesId(), SalesStatus.APROVED);
                        salesConfirmationSender.sendSalesConfirmationMessage(validMessage);
                    });
        }catch (Exception e) {
            log.error("Erro ao tentar atualizar o stock message: {}", e.getMessage(), e);
            var rejectedMessage = new SalesConfirmationDTO(productStockDTO.getSalesId(), SalesStatus.REGECTED);
            salesConfirmationSender.sendSalesConfirmationMessage(rejectedMessage);
        }

    }

    private void validateStockUpdateData(ProductStockDTO dto){
        if(dto == null || dto.getSalesId().isEmpty()){
            throw new IllegalArgumentException("Produto e id da venda não deve ser nulo/vazio");
        }
        if(dto.getProducts().isEmpty()){
            throw new IllegalArgumentException("Produtos da venda não podem ser nulos/vazios");
        }

        dto.getProducts().forEach( s -> {
            if(s.getQuantity() == null || s.getProductId() == null){
                throw new IllegalArgumentException("Produto id e quantidade não podem ser nulos/vazios");
            }
        });
    }
}
