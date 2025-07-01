package com.studyspring.todocrud.controller;

import com.studyspring.todocrud.dto.JwtResponseDto;
import com.studyspring.todocrud.dto.LoginDto;
import com.studyspring.todocrud.dto.RegisterDto;
import com.studyspring.todocrud.jwt.JwtUtil;
import com.studyspring.todocrud.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto dto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            String token = jwtUtil.generateToken(dto.getUsername());
            return ResponseEntity.ok(new JwtResponseDto(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new JwtResponseDto("Invalid username or password"));
        }
    }
}
