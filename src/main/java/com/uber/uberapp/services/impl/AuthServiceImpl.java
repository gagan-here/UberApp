package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.SignupDto;
import com.uber.uberapp.dto.UserDto;
import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.enums.Role;
import com.uber.uberapp.exceptions.RunTimeConflictException;
import com.uber.uberapp.repositories.UserRepository;
import com.uber.uberapp.services.AuthService;
import com.uber.uberapp.services.RiderService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final RiderService riderService;

  @Override
  public String login(String email, String password) {
    return "";
  }

  @Override
  public UserDto signup(SignupDto signupDto) {
    userRepository
        .findByEmail(signupDto.getEmail())
        .orElseThrow(
            () ->
                new RunTimeConflictException(
                    "Cannot signup, User already exists with email " + signupDto.getEmail()));

    User mappedUser = modelMapper.map(signupDto, User.class);
    mappedUser.setRoles(Set.of(Role.RIDER));
    User savedUser = userRepository.save(mappedUser);

    // create user related entities
    riderService.createNewRider(savedUser);
    // TODO add wallet related services here

    return modelMapper.map(savedUser, UserDto.class);
  }

  @Override
  public DriverDto onboardNewDriver(String userId) {
    return null;
  }
}
