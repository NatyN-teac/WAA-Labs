package com.klass.lab01.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/admin")
public class AdminController {

    @GetMapping()
    public ResponseEntity<?> testAdmin(){
        var contxt = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        contxt.stream().forEach((v) -> {
            System.out.println("roles looks like here" + v.getAuthority());
        });
        return ResponseEntity.ok("Success with admin role");
    }
}
