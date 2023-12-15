package esprit.tunisiacamp.config;


import com.google.gson.Gson;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.repositories.UserRepository;
import esprit.tunisiacamp.services.CustomOAuth2UserService;
import esprit.tunisiacamp.services.UserIService;
import esprit.tunisiacamp.token.Token;
import esprit.tunisiacamp.token.TokenRepository;
import esprit.tunisiacamp.token.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.jws.soap.SOAPBinding;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig  {
/*
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/rest/login/oauth/**","/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("pass")
                .defaultSuccessUrl("/rest/swagger-ui/index.html#")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("AuthenticationSuccessHandler invoked");
                        System.out.println("Authentication name: " + authentication.getName());
                       // User u = new User();
                        //u.setProdiver(Provider.GOOGLE);
                        //u.setUsername(authentication.getName());
                        //userService.addUser(u);
                        //System.out.println("Authentication name: " + authentication.getCredentials());

                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        userIService.processOAuthPostLogin(oauthUser.getEmail());
                        response.sendRedirect("/rest/swagger-ui/index.html#");
                    }
                })
                .and()
                //.logout().logoutUrl("/rest/logout")
                //.logoutSuccessUrl("/rest/login")
                //.invalidateHttpSession(true)
                //.deleteCookies("JSESSIONID")
                //.permitAll();
                .logout().logoutSuccessUrl("/logout").permitAll()

        ;
                //.and()
                //.exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    private CustomOAuth2UserService oauthUserService;
    @Autowired
    UserIService userIService;
*/
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final JwtService jwtService;
    private UserRepository ur;
    @Autowired
    TokenRepository tokenRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.cors().disable();
       http.cors();
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/deleteOrDisableUser/**","/pay","/register","/authenticate","/login/oauth/**","/login/**","/rest/swagger-ui/index.html#/","/process_register","/verify","/affecterUserToRole/**","/resetpwd/**","/verifiePwd/**","/allUsers")
                .permitAll()
                //.antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("http://localhost:4200/login")
                .usernameParameter("email")
                .passwordParameter("pass")
                .defaultSuccessUrl("http://localhost:4200/login")
                .and()
                .oauth2Login()
                .loginPage("http://localhost:4200/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("AuthenticationSuccessHandler invoked");
                        System.out.println("Authentication name: " + authentication.getName());
                        // User u = new User();
                        //u.setProdiver(Provider.GOOGLE);
                        //u.setUsername(authentication.getName());
                        //userService.addUser(u);
                        //System.out.println("Authentication name: " + authentication.getCredentials());

                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        try {
                            userIService.processOAuthPostLogin(oauthUser.getEmail(),response,request);

                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                        //User u = new User();
                        //u =ur.getUserByUsername(oauthUser.getEmail());
                          //  System.out.println(u.getUsername());
                        //String token =jwtService.generateToken(ur.getUserByUsername(oauthUser.getEmail()));
                        //CookieUtil.create(response,"jwtToken",token);
                        //response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                        response.sendRedirect("http://localhost:4200/home");
                    }

                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/rest/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }
    @Autowired
    private CustomOAuth2UserService oauthUserService;
    @Autowired
    UserIService userIService;
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
