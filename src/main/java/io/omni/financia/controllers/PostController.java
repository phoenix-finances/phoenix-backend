package io.omni.financia.controllers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.dto.AppUserDto;
import io.omni.financia.domains.dto.PostDto;
import io.omni.financia.services.AppUserService;
import io.omni.financia.services.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    private @Resource PostService postService;
    private @Resource AppUserService appUserService;
    @GetMapping
    public AppUserDto getUserPosts(@RequestParam Long userId){
        AppUser user = appUserService.getUser(userId);

        return AppUserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .posts(user.getUserPosts().stream().map(post-> PostDto.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .content(post.getContent())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
