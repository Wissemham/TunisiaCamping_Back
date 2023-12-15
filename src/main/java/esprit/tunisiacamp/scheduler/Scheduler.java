package esprit.tunisiacamp.scheduler;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.repositories.AutorityRepository;
import esprit.tunisiacamp.repositories.RoleRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class Scheduler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    AutorityRepository autorityRepository;
    @Autowired
    RoleRepository roleRepository;

    //@Scheduled(cron = "* * * * * * ")
    public void Notification(){
        List<User> users = (List<User>) userRepository.findAll();
        for(User u : users){
            //LocalDate d = LocalDate.now();
            //LocalDate d1 = d.minusDays(3);
            if(u.isEnable() && new Date().getTime()-u.getLastCnx().getTime()>1000*3600*24*3 && u.getLastC()==false){
                u.setLastC(true);
                userRepository.save(u);
                sendSms();

            }
        }
    }
    private final String ACCOUNT_SID = "AC40a2bf2c3b42a8ca159c39d88298e173";
    private final String AUTH_TOKEN = "36c0b81a667077f30f49a8f6f1610678";
    // private final String FROM_NUMBER = "+14302492629";
    public void sendSms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21692108297"),
                new com.twilio.type.PhoneNumber("+14302492629"),
                "please sign in as soon as possible or we will deactivate your account ").create();

        System.out.println(message.getSid());
    }




}
