package com.uber.uberapp.dto;

import com.uber.uberapp.entities.enums.Role;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String name;
  private String email;
  private Set<Role> roles;
}
