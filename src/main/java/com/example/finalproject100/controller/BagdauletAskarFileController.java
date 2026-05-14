package com.example.finalproject100.controller;

import com.example.finalproject100.service.BagdauletAskarFileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class BagdauletAskarFileController {

    private final BagdauletAskarFileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
        log.info("Uploading file: {}", file.getOriginalFilename());
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
        }
        String fileName = fileStorageService.storeFile(file);
        return ResponseEntity.ok(Map.of(
                "fileName", fileName,
                "message", "File uploaded successfully"
        ));
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {
        log.info("Downloading file: {}", fileName);
        Resource resource = fileStorageService.loadFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
