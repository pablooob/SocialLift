package com.SocialLift.SocialLift.Controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class VerificationController {

    @GetMapping("/loaderio-42c07ef373476ee7b0a9a9cb3008dbe2.txt")
    public String getVerificationFile() throws IOException {
        Resource resource = new ClassPathResource("loaderio-42c07ef373476ee7b0a9a9cb3008dbe2.txt");
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }
}