package com.inventario.stock_manager.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inventario.stock_manager.model.ProductStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message= "El nombre del producto no puede estar vacio")
    private String name;
    
    @Positive
    private BigDecimal price;

    @Min(0)
    private Integer stockActual;

    @Min(5)
    private Integer stockMinimo;

    @Column(nullable = false)
    private Boolean isCritical = false;

    private String imageUrl;

    private String imagePublicId;

    @NotBlank(message= "La descripción del producto no puede estar vacía")
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name ="created_at", updatable=false)
    private LocalDateTime createdAt;

    @PrePersist
    protected  void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
