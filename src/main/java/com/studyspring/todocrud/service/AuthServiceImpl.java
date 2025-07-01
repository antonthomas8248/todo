package com.studyspring.todocrud.service;

import com.studyspring.todocrud.dto.RegisterDto;
import com.studyspring.todocrud.exception.AppException;
import com.studyspring.todocrud.model.User;
import com.studyspring.todocrud.repo.UserRepo;
import com.studyspring.todocrud.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterDto dto) {
        if (userRepo.findByUsername(dto.getUsername()).isPresent()) {
            throw new AppException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(user);

        return "User registered successfully";
    }
}
