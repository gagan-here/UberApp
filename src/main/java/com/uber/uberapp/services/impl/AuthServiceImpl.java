package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.SignupDto;
import com.uber.uberapp.dto.UserDto;
import com.uber.uberapp.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  @Override
  public String login(String email, String password) {
    return "";
  }

  @Override
  public UserDto signup(SignupDto signupDto) {
    return null;
  }

  @Override
  public DriverDto onboardNewDriver(String userId) {
    return null;
  }
}
