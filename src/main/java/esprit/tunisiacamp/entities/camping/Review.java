package esprit.tunisiacamp.entities.camping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Review implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idReview;
    int value;
    String content;
    @Temporal(TemporalType.DATE)
    Date creation;
    @ManyToOne
    @JsonIgnore
    CampingGround review_Ground;
    @ManyToOne
    @JsonIgnore
    User user;
}
