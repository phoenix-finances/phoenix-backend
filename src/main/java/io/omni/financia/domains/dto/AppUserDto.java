package io.omni.financia.domains.dto;

import io.omni.financia.domains.AppUser;
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

    public static AppUserDto from(AppUser user){
        return AppUserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
