package io.omni.financia.domains;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "d1_user_posts")
@Data
public class Post {
    @SequenceGenerator(name = "post_id_generator", sequenceName = "seq_app_post_id", allocationSize = 1)
    @GeneratedValue(generator = "post_id_generator", strategy = GenerationType.SEQUENCE)
    private @Id Long id;


    @ManyToOne
    private AppUser owner;

    private String title;

    private String content;
}
