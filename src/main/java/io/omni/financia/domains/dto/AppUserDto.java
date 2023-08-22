package io.omni.financia.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class AppUserDto {
    private Long id;
    private String email;
    private String name;

    private List<PostDto> posts;
}
