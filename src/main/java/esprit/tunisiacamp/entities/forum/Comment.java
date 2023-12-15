package esprit.tunisiacamp.entities.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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

public class Comment implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idComment;
    String contentComment;
    LocalDateTime creationComment;
    @JsonIgnore

    @ManyToOne
    User user;
    @JsonIgnore

    @ManyToOne
    Post post;

    @JsonIgnore
    @OneToMany (mappedBy = "reponsecomm", cascade = CascadeType.REMOVE)
    List<Reponse> reponses;

}
