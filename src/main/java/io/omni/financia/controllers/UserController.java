package io.omni.financia.controllers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.Post;
import io.omni.financia.domains.dto.AppUserDto;
import io.omni.financia.domains.dto.PostDto;
import io.omni.financia.services.AppUserService;
import io.omni.financia.services.PostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController{

    private @Resource AppUserService appUserService;
    private @Resource PostService postService;

    @GetMapping
    public List<AppUserDto> getAll(){
        return appUserService.getAllUsers()
                .stream().map(AppUserDto::from)
                .collect(Collectors.toList());
    }

    @PostMapping
    public AppUser insert(@RequestBody AppUser request){
        return appUserService.insert(request);
    }

    @PostMapping("/{userId}/posts")
    public PostDto createUserPost(@PathVariable Long userId, @RequestBody PostDto request){
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        AppUser user = appUserService.getUser(userId);
        post.setOwner(user);
        Post createdPost = postService.insertPost(post);

        return PostDto.builder()
                .id(createdPost.getId())
                .title(createdPost.getTitle())
                .content(createdPost.getContent())
                .build();
    }
}
