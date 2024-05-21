package com.SocialLift.SocialLift.Controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class VerificationController {

    @GetMapping("/loaderio-42c07ef373476ee7b0a9a9cb3008dbe2.txt")
    public ResponseEntity<String> getVerificationFile() {
        try {
            Resource resource = new ClassPathResource("loaderio-42c07ef373476ee7b0a9a9cb3008dbe2.txt");
            String content = new String(Files.readAllBytes(resource.getFile().toPath()));
            return new ResponseEntity<>(content, new HttpHeaders(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}