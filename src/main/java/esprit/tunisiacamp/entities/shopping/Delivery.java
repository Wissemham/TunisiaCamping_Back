package esprit.tunisiacamp.entities.shopping;

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

public class Delivery implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idDelivery;
    String location;
    @Temporal(TemporalType.DATE)
    Date delivery_date;
    float weight;
    Boolean done;
    @OneToMany(mappedBy = "delivery")
    @JsonIgnore
    List<Transaction> shipments;
    double pricedelivery;

    @ManyToOne(cascade = CascadeType.ALL)
    Transaction transaction;
    @Transient
    double distanceFromDelivery;

    public void setDistanceFromDelivery(double distance) {
        this.distanceFromDelivery = distance;
    }

    public double getDistanceFromDelivery() {
        return this.distanceFromDelivery;
    }
}
