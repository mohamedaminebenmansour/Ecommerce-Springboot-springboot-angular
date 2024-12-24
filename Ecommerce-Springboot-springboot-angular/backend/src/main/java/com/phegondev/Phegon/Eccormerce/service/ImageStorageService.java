package com.phegondev.Phegon.Eccormerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStorageService {

    private final String imagePath = "uploads/images/"; // Adjust as needed

    public String saveImage(MultipartFile image) {
        try {
        	// 1. Generate a unique filename (e.g., using UUID)
            String imageFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename(); 

            // 2. Define the upload directory path
            String uploadDir = "src/main/resources/static/images/"; // Adjust this path as needed

            // 3. Create the upload directory if it doesn't exist
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 4. Save the image to the upload directory
            Path filePath = Paths.get(uploadDir, imageFileName);
            Files.write(filePath, image.getBytes());
            return "assets/images/products/placeholder.png"+image.getOriginalFilename(); // Assuming your application serves static files from the 'images' directory
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image here", e);
        }
    }
}
