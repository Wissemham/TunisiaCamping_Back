package esprit.tunisiacamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.enums.Category;
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

public class Claim implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idClaim;
    @Temporal(TemporalType.DATE)
    Date creation;
    String title;
    @Enumerated(EnumType.STRING)
    Category category;
    String content;
    boolean state;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "admin_id_user")
    User admin;
    @ManyToOne
    @JoinColumn(name = "user_id_user")
    User user;
    @Transient
    int sentimentScore;
}
