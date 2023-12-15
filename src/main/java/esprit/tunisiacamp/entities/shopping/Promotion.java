package esprit.tunisiacamp.entities.shopping;

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

public class Promotion implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idPromotion;
    String title;
    String Description;
    @Temporal(TemporalType.DATE)
    Date start_date;
    @Temporal(TemporalType.DATE)
    Date end_date;
    float discount;
    @OneToOne(mappedBy = "promotion")
    Transaction transaction;
}
