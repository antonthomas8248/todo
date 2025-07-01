package com.studyspring.todocrud.service;

import com.studyspring.todocrud.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto dto);
}
