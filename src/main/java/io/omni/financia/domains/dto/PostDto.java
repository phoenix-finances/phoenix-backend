package io.omni.financia.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class PostDto {
    private Long id;
    // TODO add validations
    private String title;
    private String content;
}
