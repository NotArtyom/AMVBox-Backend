package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.demo.service.FileStorageService;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class StorageController {

    @Autowired
    private FileStorageService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/files")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String filePath = service.saveFile(file);
        return ResponseEntity
                .ok()
                .body(filePath);
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.writeFileToResponse(fileName, response);
    }
}
