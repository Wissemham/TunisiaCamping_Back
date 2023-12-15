package esprit.tunisiacamp.entities.shopping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.*;
import esprit.tunisiacamp.entities.enums.Season;
import esprit.tunisiacamp.entities.enums.Variety;
import esprit.tunisiacamp.entities.shopping.Critique;
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

public class Tool implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idTool;
    String name;
    @Enumerated(EnumType.STRING)
    Variety variety;
    @Enumerated(EnumType.STRING)
    Season season;
    float price;
    boolean availability;
    int stock;
    boolean by_shop;
    @Enumerated(EnumType.STRING)
    Type type;
    @JsonIgnore
    @OneToMany(mappedBy = "tool" )
    List<Critique> critiques;
    @JsonIgnore
    @ManyToMany(mappedBy = "my_tools")
    List<User> owners;

}
