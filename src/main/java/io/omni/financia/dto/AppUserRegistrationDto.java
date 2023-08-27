package io.omni.financia.dto;

import lombok.Data;

@Data
public class AppUserRegistrationDto {
    private String name;
    private String email;
    private String plainTextPassword;
}
