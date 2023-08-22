package io.omni.financia.domains.repository;

import io.omni.financia.domains.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
