package esprit.tunisiacamp.entities.camping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class GroupCamp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long idCroup;
    boolean etat;
    String name;
    @ManyToOne
    @JsonIgnore
    Reservation reservation_grw;
    @ManyToOne
    @JsonIgnore
    User user_grw;
}
