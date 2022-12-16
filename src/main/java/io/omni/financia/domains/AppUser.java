package io.omni.financia.domains;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "d1_users")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id
    //@SequenceGenerator(name = "seq_app_gen", sequenceName = "seq_app_user_id", allocationSize = 1)
    // https://stackoverflow.com/questions/39861098/replace-sequencegenerator-since-its-deprecated
    @GenericGenerator(name = "app_user_id_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
            @Parameter(name = "sequence_name", value = "seq_app_user_id"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1")
    })
    @GeneratedValue(generator = "seq_app_gen", strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    private String email;
}
