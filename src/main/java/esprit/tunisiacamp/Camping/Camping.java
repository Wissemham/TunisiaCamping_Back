package esprit.tunisiacamp.Camping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.*;
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
public class Camping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private int groupSize;
    private String preferredCampground;
    @Temporal(TemporalType.DATE)
    private Date checkInDate;
    @Temporal(TemporalType.DATE)
    private Date checkOutDate;
    //private String specialRequests;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User createur;
    @JsonIgnore
    @OneToMany(mappedBy = "campingGroupe",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<User> listgroup;
}
