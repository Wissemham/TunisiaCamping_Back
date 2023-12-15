package esprit.tunisiacamp.entities.shopping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Critique implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idCritique;
    int rating;
    String content;
    long user_id_user;
    @JsonIgnore
    @ManyToOne
    Tool tool;

}
