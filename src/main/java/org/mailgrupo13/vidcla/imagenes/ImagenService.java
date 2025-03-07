package org.mailgrupo13.vidcla.imagenes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ImagenService {

    @Value("${upload.dir}")
    private String uploadDir;


    @Autowired
    private ImagenRepository imagenRepository;


    private static final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");
    private static final long MAX_SIZE = 2 * 1024 * 1024; // 2 MB

    public String CargarImagen(MultipartFile file) {
        if (file.isEmpty()) {
             ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file to upload.");
        }

        if (!ALLOWED_TYPES.contains(file.getContentType())) {
             ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only JPEG, PNG, and GIF files are allowed.");
        }

        if (file.getSize() > MAX_SIZE) {
             ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size exceeds the 2 MB limit.");
        }

        try {
            // Ensure the upload directory exists
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // Save the file locally
            byte[] bytes = file.getBytes();
            String randomFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + "/" + randomFileName);
            Files.write(path, bytes);

            return randomFileName;
        } catch (IOException e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
        return null;
    }



    public ResponseEntity<byte[]> DescargarImagen(String filename) {
        try {
            Path filePath = Paths.get(uploadDir, filename);
            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            byte[] fileContent = Files.readAllBytes(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the content type based on your needs
            headers.setContentDispositionFormData(filename, filename);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    public ImagenDTO create(Imagen imagen) {
        return convertToDTO(imagenRepository.save(imagen));
    }

    public ImagenDTO convertToDTO(Imagen imagen) {
        return new ImagenDTO(imagen.getId(), imagen.getNombre(), imagen.getVehiculo().getId());
    }



}