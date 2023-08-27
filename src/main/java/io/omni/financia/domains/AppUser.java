package io.omni.financia.domains;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "app_users")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
public class AppUser extends AbstractEntity {
    private String name;
    private String email;
    private String password;

    public AppUser() {
    }

    public AppUser(Long id) {
        super(id);
    }
}
