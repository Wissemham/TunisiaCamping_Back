package esprit.tunisiacamp.entities.shopping;

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

public class Fidelity implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idFidelity;
    int value;

}
