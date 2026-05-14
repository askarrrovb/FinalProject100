package com.example.finalproject100.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class BagdauletAskarFileStorageService {

    private final Path uploadDir;

    public BagdauletAskarFileStorageService(@Value("${file.upload-dir}") String uploadDir) {
        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originalName;
        try {
            Path targetPath = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            log.info("File stored: {}", fileName);
            return fileName;
        } catch (IOException e) {
            log.error("Failed to store file: {}", originalName, e);
            throw new RuntimeException("Could not store file " + originalName, e);
        }
    }

    public Resource loadFile(String fileName) {
        try {
            Path filePath = uploadDir.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        }
    }
}
