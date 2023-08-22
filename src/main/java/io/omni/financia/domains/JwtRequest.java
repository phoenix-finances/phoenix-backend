package io.omni.financia.domains;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}