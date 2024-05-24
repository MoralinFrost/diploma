package com.example.diploma.Entity;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
  private String email;
  private String password;
  private String token;
}
