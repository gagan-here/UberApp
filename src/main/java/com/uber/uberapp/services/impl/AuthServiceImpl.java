package com.uber.uberapp.services.impl;

import com.uber.uberapp.dto.DriverDto;
import com.uber.uberapp.dto.SignupDto;
import com.uber.uberapp.dto.UserDto;
import com.uber.uberapp.entities.Driver;
import com.uber.uberapp.entities.User;
import com.uber.uberapp.entities.enums.Role;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.exceptions.RunTimeConflictException;
import com.uber.uberapp.repositories.UserRepository;
import com.uber.uberapp.security.JwtService;
import com.uber.uberapp.services.AuthService;
import com.uber.uberapp.services.DriverService;
import com.uber.uberapp.services.RiderService;
import com.uber.uberapp.services.WalletService;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;

  private final RiderService riderService;
  private final WalletService walletService;
  private final DriverService driverService;
  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  private final ModelMapper modelMapper;

  @Override
  public String[] login(String email, String password) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password));

    User user = (User) authentication.getPrincipal();

    String accessToken = jwtService.generateAccessToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);

    return new String[] {accessToken, refreshToken};
  }

  @Override
  @Transactional
  public UserDto signup(SignupDto signupDto) {
    Optional<User> user = userRepository.findByEmail(signupDto.getEmail());

    if (user.isPresent()) {
      throw new RunTimeConflictException(
          "Cannot signup, User already exists with email " + signupDto.getEmail());
    }

    User mappedUser = modelMapper.map(signupDto, User.class);
    mappedUser.setRoles(Set.of(Role.RIDER));
    mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
    User savedUser = userRepository.save(mappedUser);

    // create user related entities
    riderService.createNewRider(savedUser);
    walletService.createNewWallet(savedUser);

    return modelMapper.map(savedUser, UserDto.class);
  }

  @Override
  public DriverDto onboardNewDriver(Long userId, String vehicleId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

    if (user.getRoles().contains(Role.DRIVER))
      throw new RunTimeConflictException("User with id " + userId + " is already a driver");

    Driver createdDriver =
        Driver.builder().user(user).rating(0.0).vehicleId(vehicleId).available(true).build();
    user.getRoles().add(Role.DRIVER);
    userRepository.save(user);

    Driver savedDriver = driverService.createNewDriver(createdDriver);
    return modelMapper.map(savedDriver, DriverDto.class);
  }

  @Override
  public String refreshToken(String refreshToken) {
    Long userId = jwtService.getUserIdFromToken(refreshToken);
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    return jwtService.generateAccessToken(user);
  }
}
