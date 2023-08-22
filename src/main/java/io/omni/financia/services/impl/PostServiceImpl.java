package io.omni.financia.services.impl;

import io.omni.financia.domains.Post;
import io.omni.financia.domains.repository.PostRepository;
import io.omni.financia.services.PostService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private @Resource PostRepository postRepository;

    @Override
    public List<Post> getPostsByUser(Long userId) {
        return null;
    }

    @Override
    public Post insertPost(Post request) {
        return postRepository.save(request);
    }
}
