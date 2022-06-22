package com.microservice.productapi.product.rabbitmq;

import com.microservice.productapi.product.dto.ProductStockDTO;
import com.microservice.productapi.product.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductStockListener {

    @Autowired
    private ProductService service;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void recieveProductStockMessage(ProductStockDTO productStockDTO){
        service.updateProductStock(productStockDTO);
    }
}
