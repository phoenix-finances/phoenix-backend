package io.omni.financia.domains;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "d1_user_posts")
public class Post {
    private @Id Long id;
}
