package esprit.tunisiacamp.services;

import com.google.gson.Gson;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import esprit.tunisiacamp.config.CookieUtil;
import esprit.tunisiacamp.config.JwtService;
import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Provider;
import esprit.tunisiacamp.entities.enums.State;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.repositories.AutorityRepository;
import esprit.tunisiacamp.repositories.RoleRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import esprit.tunisiacamp.token.Token;
import esprit.tunisiacamp.token.TokenRepository;
import esprit.tunisiacamp.token.TokenType;
import lombok.var;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

//import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class UserService implements UserIService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    AutorityRepository autorityRepository;
    private final PasswordEncoder passwordEncoder;

   // private final JwtService jwtService;
    @Autowired
    JwtService jwtService;
    @Autowired
    TokenRepository tokenRepository;
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User FindById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> allUsers() {
        return (List<User>) userRepository.allUser();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id, State state) {
        userRepository.deleteUser(id,state);
    }

    @Override
    public void affecterUserToRole(Integer idUser, long idRole) {
        Role role = roleRepository.findById(idRole).get();
        User user = userRepository.findById(idUser).get();
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void register(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnable(false);
        String pwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(pwd);
        user.setProdiver(Provider.Local);
        user.setLastC(false);

        userRepository.save(user);
        //User u = userRepository.findByEmail(user.getEmail()).get();
        //Autority a = new Autority();
        //a.setUserAuth(u);
        //a.setName(u.getRole().toString());
        //autorityRepository.save(a);
        sendVerificationEmail(user, siteURL);
    }



    @Override
    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "aladin.hammouda@esprit.tn";
        String senderName = "Camping";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Camping.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnable()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnable(true);
            userRepository.save(user);

            return true;
        }
    }

    public void processOAuthPostLogin(String email, HttpServletResponse response, HttpServletRequest request) {
        User existUser = userRepository.getUserByUsername(email);


        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(email);
            String randomCode = RandomString.make(10);
            String pwd = passwordEncoder.encode(randomCode);
            newUser.setPassword(pwd);
            newUser.setProdiver(Provider.GOOGLE);
            newUser.setEnable(true);
            newUser.setLastC(false);
            Role r =roleRepository.getRole(role.CAMPER);
            newUser.setRole(r);
            Autority a = new Autority();
            a.setName("CAMPER");
            a.setUserAuth(newUser);
            //CookieUtil.create(response,"jwtToken","hbvkfdsbkjvbkfdsjbkjvdnf");
            userRepository.save(newUser);
            autorityRepository.save(a);
            //sendSms1(randomCode);

        }
        User u = new User();
        u=userRepository.getUserByUsername(email);
        String jwtToken = jwtService.generateToken(u);
        saveUserToken(u, jwtToken);
        CookieUtil.create(response, "jwtToken", jwtToken);
        String ro = new Gson().toJson("CAMPER");
        CookieUtil.create(response, "roles", ro);
        String id = String.valueOf(u.getIdUser());
        CookieUtil.create(response,"id",id);
       // System.out.println(u.getIdUser());
        HttpSession session = request.getSession();
        session.setAttribute("user",u.getEmail());



    }
    @Override
    public void sendSms1(String code) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21692108297"),
                new com.twilio.type.PhoneNumber("+14302492629"),
                "Reminder: Your password is "+code +"Thanks for visite our site").create();

        System.out.println(message.getSid());
    }

    @Override
    public String chengePassword(Integer id, String newPassword) {
        User u = userRepository.findById(id).get();
        u.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(u);
        return "password change";
    }

    private final String ACCOUNT_SID = "AC40a2bf2c3b42a8ca159c39d88298e173";
    private final String AUTH_TOKEN = "6e89ac400416aa7c0ccad16501b86505";
    // private final String FROM_NUMBER = "+14302492629";
    @Override
    public void sendSms(String code) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21692108297"),
                new com.twilio.type.PhoneNumber("+14302492629"),
                "Reminder: Your code is "+code).create();

        System.out.println(message.getSid());
    }

    @Override
    public void resetPassword(String email) {
        User user = userRepository.getUserByUsername(email);
        String randomCode = RandomString.make(6);
        user.setVerifiepwd(randomCode);
        userRepository.save(user);
        //sendSms(randomCode);


    }

    @Override
    public String verifiePwd(String code, String pwd) {
        //User user = userRepository.getUserByVerifiepwd(code);
        //System.out.println(user.getIdUser());
        User user = userRepository.getUserCD(code);
        if(user!=null){
            user.setPassword(passwordEncoder.encode(pwd));
            user.setVerifiepwd(null);
            userRepository.save(user);
            return "valide";
        }
        return "Verifie your code";
    }
    private void saveUserToken(User user, String jwtToken) {

        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
