package com.inventario.stock_manager.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    

    // Metodo para subir la imagen a cloudinary
    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);

        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        // Borrar el archivo de forma temporal
        if(!file.delete()) {
            throw new IOException("No se pudo eliminar el archivo temporal");
        }

        return result;

    }

    // Meto para borrar la imagen de cloudinary
    public Map delete(String publicId) throws IOException {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    // Helper para convertir MultipartFile a File usando try-with-resources
    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        
        try (FileOutputStream fo = new FileOutputStream(file)) {
            fo.write(multipartFile.getBytes());
        }
        return file;
    }
    
}
