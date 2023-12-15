package esprit.tunisiacamp.entities.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
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

public class Rating implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idRating;
    int value;
    @JsonIgnore

    @ManyToOne
    Post post_rate;
    @JsonIgnore

    @ManyToOne
    User user;
}
