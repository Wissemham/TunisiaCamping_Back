package esprit.tunisiacamp.config;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
   // private User user;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
         User user = userRepository.getUserByUsername(username);

         //user = userRepository.getUserByUsername(username);
        //User user = new User();

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}
