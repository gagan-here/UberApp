package com.uber.uberapp.entities;

import com.uber.uberapp.entities.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "app_user",
    indexes = {@Index(name = "idx_user_email", columnList = "email")})
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true)
  private String email;

  private String password;

  @ElementCollection(fetch = FetchType.LAZY)
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;
}
