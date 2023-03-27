package socNet.Stadium.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "system_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="system_user_id_seq")
    Long id;

    String email;

    String password;

    String role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Profile profile;
}
