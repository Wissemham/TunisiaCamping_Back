package esprit.tunisiacamp.entities.forum;

import esprit.tunisiacamp.entities.User;
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

public class ChatRoom implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idChatRoom;


    String nameRoom;
    @Temporal(TemporalType.TIME)
    Date creationRoom;
    @ManyToMany(mappedBy = "chatRooms")
    List<User> users;
    @OneToMany(mappedBy = "chatRoom")
    List<Messagee> messagees;

}
