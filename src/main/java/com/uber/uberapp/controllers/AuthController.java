package com.uber.uberapp.controllers;

import com.uber.uberapp.dto.SignupDto;
import com.uber.uberapp.dto.UserDto;
import com.uber.uberapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signup")
  UserDto signUp(@RequestBody SignupDto signupDto) {
    return authService.signup(signupDto);
  }
}
