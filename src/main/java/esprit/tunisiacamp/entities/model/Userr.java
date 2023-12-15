package esprit.tunisiacamp.entities.model;


import javax.persistence.*;

@Table(name = "users")
@Entity
public class Userr {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int iduser ;

     String username;

    public Userr() {
    }

    public Userr(String userName) {
        this.username = userName;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
