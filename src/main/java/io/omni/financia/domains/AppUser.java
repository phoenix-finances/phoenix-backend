package io.omni.financia.domains;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "d1_users")
@Data
@Builder
@AllArgsConstructor
public class AppUser implements UserDetails {
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

    public AppUser() {
    }

    public AppUser(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
