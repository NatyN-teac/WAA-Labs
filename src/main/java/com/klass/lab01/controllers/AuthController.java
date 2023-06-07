package com.klass.lab01.controllers;

import com.klass.lab01.dto.request.LoginBody;
import com.klass.lab01.dto.request.RegisterBody;
import com.klass.lab01.dto.request.UserDto;
import com.klass.lab01.dto.response.AuthResponse;
import com.klass.lab01.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/authenticate")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginBody loginBody) {
        return ResponseEntity.ok(authService.login(loginBody));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterBody registerBody) {
        return ResponseEntity.ok(authService.register(registerBody));
    }
}
