package esprit.tunisiacamp.entities.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Reponse implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idReponse;
    String Reponsecom;

    LocalDateTime dateReponse;

    @JsonIgnore
    @ManyToOne
    Comment reponsecomm  ;
    @JsonIgnore
    @ManyToOne
    User userreponse ;

}
