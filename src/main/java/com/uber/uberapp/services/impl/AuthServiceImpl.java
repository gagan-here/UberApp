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
import com.uber.uberapp.services.WalletService;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;

  private final RiderService riderService;
  private final WalletService walletService;

  private final ModelMapper modelMapper;

  @Override
  public String login(String email, String password) {
    return "";
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
    User savedUser = userRepository.save(mappedUser);

    // create user related entities
    riderService.createNewRider(savedUser);
    walletService.createNewWallet(savedUser);

    return modelMapper.map(savedUser, UserDto.class);
  }

  @Override
  public DriverDto onboardNewDriver(Long userId) {
    return null;
  }
}
