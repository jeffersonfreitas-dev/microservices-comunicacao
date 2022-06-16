package com.microservice.productapi.supplier.model;

import com.microservice.productapi.category.dto.CategoryRequest;
import com.microservice.productapi.category.model.Category;
import com.microservice.productapi.supplier.dto.SupplierRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SUPPLIER")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;

    public static Supplier of(SupplierRequest request){
        var entity = new Supplier();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
