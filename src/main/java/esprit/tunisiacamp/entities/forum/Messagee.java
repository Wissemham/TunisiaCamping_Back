package esprit.tunisiacamp.entities.forum;

import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Messagee implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idMessage;
    String contentMessage;
    @Temporal(TemporalType.TIME)
    Date creationMessage;
    @ManyToOne
    ChatRoom chatRoom;
    @ManyToOne
    User user;
}
