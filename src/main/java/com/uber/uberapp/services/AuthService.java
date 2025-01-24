package com.uber.uberapp.services;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.SignupDto;
import com.uber.uberapp.dto.UserDto;

public interface AuthService {
  String[] login(String email, String password);

  UserDto signup(SignupDto signupDto);

  DriverDto onboardNewDriver(Long userId, String vehicleId);

  String refreshToken(String refreshToken);
}
