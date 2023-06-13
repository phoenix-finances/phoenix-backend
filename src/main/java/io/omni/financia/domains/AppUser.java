package io.omni.financia.domains;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "d1_users")
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class AppUser extends StringEntity{
    @Id
    @SequenceGenerator(name = "seq_app_gen", sequenceName = "seq_app_user_id", allocationSize = 1)
    // https://stackoverflow.com/questions/39861098/replace-sequencegenerator-since-its-deprecated
    /*@GenericGenerator(name = "app_user_id_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
            @Parameter(name = "sequence_param", value = "seq_app_user_id"),
            @Parameter(name = "initial_param", value = "1"),
            @Parameter(name = "increment_param", value = "1")
    })*/
    @GeneratedValue(generator = "seq_app_gen", strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    private String email;
    private String password;

    // https://stackoverflow.com/questions/59777807/how-to-ignore-onetomany-relation-for-get-request
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner") // without mappedBy, hibernate created additional table d1_users_user_posts
    private List<Post> userPosts;
}
