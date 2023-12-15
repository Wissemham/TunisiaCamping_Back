package esprit.tunisiacamp.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.List;

import esprit.tunisiacamp.entities.enums.role;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role implements Serializable {

     @Id
     @GeneratedValue (strategy = GenerationType.IDENTITY)
     long idRole;
     @Enumerated(EnumType.STRING)
     role role;

     @OneToMany(mappedBy = "role")
     List<User> users;


}
