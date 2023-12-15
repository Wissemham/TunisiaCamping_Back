package esprit.tunisiacamp.entities.shopping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

public class Transaction implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idTransaction;
    String serial_code=this.serial_code_gen();
    @Enumerated(EnumType.STRING)
    Type type;
    @Temporal(TemporalType.DATE)
    Date creation;
    @Temporal(TemporalType.DATE)
    Date rent_start_date;
    @Temporal(TemporalType.DATE)
    Date rent_end_date;
    float price;
    Boolean paid;
    Boolean for_shipment;
    String payment_method;

    @ManyToOne
    Tool tool;
    @JsonIgnore
    @OneToOne
    Promotion promotion;
    @JsonIgnore
    @ManyToOne
    User shopper;
    @JsonIgnore
    @ManyToOne
    Delivery delivery;
    String serial_code_gen(){
        long base = System.currentTimeMillis();
        LocalDate now= LocalDate.now();
        // Combine the ms time and current date to create the serial code
        return Long.toString(base) + now;
    }
    @OneToMany(mappedBy = "transaction")
    @JsonIgnore
    List<Delivery> deliveryList;
}
