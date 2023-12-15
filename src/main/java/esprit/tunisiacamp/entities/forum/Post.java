package esprit.tunisiacamp.entities.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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

public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idPost;
    String ContentPost;

    LocalDateTime  creation;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> comments;
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore

    @OneToMany(mappedBy = "post_rate", cascade = CascadeType.REMOVE)
    List<Rating> ratings;
}
