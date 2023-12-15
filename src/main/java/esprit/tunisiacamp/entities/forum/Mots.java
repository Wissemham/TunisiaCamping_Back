package esprit.tunisiacamp.entities.forum;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Mots implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idMots ;
    String mots ;
}
