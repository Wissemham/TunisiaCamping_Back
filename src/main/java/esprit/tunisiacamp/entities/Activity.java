package esprit.tunisiacamp.entities;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Favorite;
import esprit.tunisiacamp.entities.enums.Grouping;
import esprit.tunisiacamp.entities.enums.Season;
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

public class Activity implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idActivitiy;
    String name;
    @Enumerated(EnumType.STRING)
    Season season;
    String Duration;
    @Enumerated(EnumType.STRING)
    Grouping grouping;
    @ManyToMany(mappedBy = "activities")
    List<CampingGround> campingGrounds;
    @OneToMany(mappedBy = "activity_fav")
    List<Favorite> favorites;
}
