package com.raj.ams.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file, String filename) throws IOException {
        Path path = Paths.get(uploadDir).resolve(filename);
        Files.createDirectories(path.getParent());  // Ensure directory exists
        Files.write(path, file.getBytes());
        return path.toString();  // Return path as a string
    }
}


