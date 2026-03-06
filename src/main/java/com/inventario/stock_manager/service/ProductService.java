package com.inventario.stock_manager.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inventario.stock_manager.dto.ProductCreateRequest;
import com.inventario.stock_manager.dto.ProductDTO;
import com.inventario.stock_manager.entity.Product;
import com.inventario.stock_manager.model.ProductStatus;
import com.inventario.stock_manager.repository.ProductRepository;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;

    public ProductService(ProductRepository productRepository, CloudinaryService cloudinaryService) {
        this.productRepository = productRepository;
        this.cloudinaryService = cloudinaryService;
    }

    // Crear un producto (entrada desde multipart/form-data)
    public ProductDTO createProduct(ProductCreateRequest dto, MultipartFile image) throws IOException {

        // Validar que el stock no sea negativo
        if (dto.getStockActual() != null && dto.getStockActual() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockActual(dto.getStockActual());
        product.setStockMinimo(dto.getStockMinimo());
        product.setStatus(ProductStatus.ACTIVE);

        // Lógica para subir la imagen a Cloudinary
        if (image != null && !image.isEmpty()) {
            var uploadResult = cloudinaryService.upload(image);
            product.setImageUrl(uploadResult.get("url").toString());
            product.setImagePublicId(uploadResult.get("public_id").toString());
        } else {
            // si no hay imagen, podemos dejar la que venga en el dto o una por defecto
            product.setImageUrl(dto.getImageUrl());
        }

        // Calcular criticidad según stock
        product.setIsCritical(product.getStockActual() < product.getStockMinimo());
        
        Product saveProduct = productRepository.save(product);

        return new ProductDTO(
            saveProduct.getId(),
            saveProduct.getName(),
            saveProduct.getDescription(),
            saveProduct.getPrice(),
            saveProduct.getStockActual(),
            saveProduct.getStockMinimo(),
            saveProduct.getImageUrl(),
            saveProduct.getIsCritical(),
            saveProduct.getStatus(),
            saveProduct.getCreatedAt()
            
        );
    }

    // Listar todos los productos
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    
    // Obtener producto por id
    public Product getProductById(Long id){
        // validar que el id no sea nulo antes de ir a la db
        if(id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con el id" + id));
    }

    // Actualizar producto
    public Product updateProduct(Long id, Product details){
        Product product = getProductById(id);

        product.setName(details.getName());
        product.setDescription(details.getDescription());
        product.setPrice(details.getPrice());
        product.setStockActual(details.getStockActual());
        product.setStockMinimo(details.getStockMinimo());

        product.setIsCritical(product.getStockActual() < product.getStockMinimo());

        return productRepository.save(product);
    }

    // Eliminar el producto
    public void deleteProduct(Long id) {
        Product product = getProductById(id);

        // Si tiene una imagen en cloudinary, la borramos de la nube tambien 
        if(product.getImagePublicId() != null) {
            try {
                cloudinaryService.delete(product.getImageUrl());
            } catch (IOException e) {
                // Error silencioso - la imagen en Cloudinary se puede limpiar manualmente
            }
        }

        productRepository.delete(product);
    }
}
