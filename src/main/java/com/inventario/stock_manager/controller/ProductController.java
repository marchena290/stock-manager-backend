package com.inventario.stock_manager.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inventario.stock_manager.dto.ProductCreateRequest;
import com.inventario.stock_manager.dto.ProductDTO;
import com.inventario.stock_manager.entity.Product;
import com.inventario.stock_manager.service.ProductService;


@RestController()
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    // CREATE -> 201 Created
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
        @RequestParam("name") String name,
        @RequestParam("description") String description,
        @RequestParam("price") BigDecimal price,
        @RequestParam("stockActual") Integer stockActual,
        @RequestParam(value = "stockMinimo", defaultValue = "5") Integer stockMinimo,
        @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        // Construir el DTO manualmente
        ProductCreateRequest dto = new ProductCreateRequest();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setStockActual(stockActual);
        dto.setStockMinimo(stockMinimo);
        
        try {
            ProductDTO saveProductDTO = productService.createProduct(dto, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveProductDTO);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // READ ALL -> 200 OK
    @GetMapping()
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // UPDATE -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }
    
    // DELETE -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
}
