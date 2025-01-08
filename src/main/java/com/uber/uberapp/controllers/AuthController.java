package com.uber.uberapp.controllers;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.SignupDto;
import com.uber.uberapp.dto.UserDto;
import com.uber.uberapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
  ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
    return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
  }

  @PostMapping("/onBoardNewDriver/{userId}")
  ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId) {
    return new ResponseEntity<>(authService.onboardNewDriver(userId), HttpStatus.CREATED);
  }
}
