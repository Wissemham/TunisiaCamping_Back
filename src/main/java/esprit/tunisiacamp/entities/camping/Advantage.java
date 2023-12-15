package esprit.tunisiacamp.entities.camping;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Advantage implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idAdvantage;
    String name;
    String type;
    @ManyToMany(mappedBy = "advantages")
    @JsonIgnore
    List<CampingGround> campingGrounds;

}
