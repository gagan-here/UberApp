package com.uber.uberapp.services;

import com.uber.uberapp.entities.User;
import com.uber.uberapp.exceptions.ResourceNotFoundException;
import com.uber.uberapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserService implements UserDetailsService {

  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }

  public User getUserById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
  }
}
