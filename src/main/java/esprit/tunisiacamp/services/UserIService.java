package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserIService {
    void addUser(User user);
    User FindById(Integer id);
    List<User> allUsers();
    void updateUser(User user);
    void deleteUser(Integer id, State state);
    void affecterUserToRole(Integer idUser,long idRole);
    public void register(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;
    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
    public boolean verify(String verificationCode);
    public void processOAuthPostLogin(String username, HttpServletResponse response, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;
     public void resetPassword(String email);
     public String verifiePwd(String code,String pwd);
    public void sendSms(String code);
    public void sendSms1(String code);
    public String chengePassword(Integer id,String newPassword);
}
