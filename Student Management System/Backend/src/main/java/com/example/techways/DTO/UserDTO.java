package com.example.techways.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private boolean accountNonLocked;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;

    private LocalDate credentialExpirationDate;
    private LocalDate accountExpirationDate;
    private String twoFactorSecret;
    private boolean isTwoFactorEnabled;
    private String signUpMethod;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
