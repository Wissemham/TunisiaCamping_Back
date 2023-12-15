package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;
import esprit.tunisiacamp.repositories.UserRepository;
import esprit.tunisiacamp.services.UserIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {
    @Autowired
    UserIService userIService;
    @Autowired
    UserRepository repository;
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userIService.addUser(user);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/FindById/{id}")
    public User FindById(@PathVariable Integer id){
        return userIService.FindById(id);
    }
    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        userIService.updateUser(user);
    }

    @GetMapping("/allUsers")
    public List<User> allUsers(){
        return userIService.allUsers();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/deleteOrDisableUser/{id}/{state}")
    public void deleteUser(@PathVariable Integer id,@PathVariable State state){
        userIService.deleteUser(id,state);
    }
    @PutMapping("/affecterUserToRole/{iduser}/{idrole}")
    public void affecterUserToRole(@PathVariable("iduser") Integer idUser ,@PathVariable("idrole") long idRole){
        userIService.affecterUserToRole(idUser,idRole);
    }

    @PostMapping("/process_register")
    public String processRegister(@RequestBody User user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        userIService.register(user, getSiteURL(request));
        return "register_success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userIService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String hello(){
        return "hello";
    }
/*
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }
*/
    //@PreAuthorize("hasAnyAuthority('CAMPER')")
    @PutMapping("/resetpwd/{email}")
    public void resetPwd(@PathVariable("email") String email){
        userIService.resetPassword(email);
    }
    @GetMapping("/verifiePwd/{code}/{pwd}")
    public  String verifiePwd(@PathVariable("code") String code,@PathVariable("pwd") String pwd){
        return userIService.verifiePwd(code,pwd);
    }


    @GetMapping("/Getbymail")
            public User getBymail(){
        return repository.findByEmail("benabdallahjalel@esprit.tn").get();
    }

    @PutMapping("/changepassword/{id}/{password}")
    public String changePassword(@PathVariable("id") Integer id,@PathVariable("password") String password){
        return userIService.chengePassword(id, password);
    }
        }
