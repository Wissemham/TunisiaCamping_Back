package esprit.tunisiacamp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private String role;
  private Date birthday;
  private int telephone;
  private int cin;
  private String adresse;

}
