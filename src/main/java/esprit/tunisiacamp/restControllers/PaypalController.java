package esprit.tunisiacamp.restControllers;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import esprit.tunisiacamp.paypal.Order;
import esprit.tunisiacamp.paypal.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;

@Configuration
@RestController
public class PaypalController {
    @Autowired
    PaypalService service;


    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping("/hme")
    public String home() {
        return  "home";
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/pay")
    public ResponseEntity<String> payment(@RequestBody Order order) {
        return ResponseEntity.ok(service.payment(order));}
       /* try {
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(),"http://localhost:9090/rest/" + CANCEL_URL,
                    "http://localhost:9090/rest/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    System.out.println("success");
                    return new RedirectView(link.getHref());
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return new RedirectView("http://localhost:9090/rest/cancel");
    }*/
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CANCEL_URL)
    public RedirectView cancelPay() {

        return new RedirectView("http://localhost:1111/rest/cancel");
       // return "cancel";
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = SUCCESS_URL)
    public RedirectView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {

                return new RedirectView("http://localhost:4200/success");
               // return (" sucsess");
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return new RedirectView("http://localhost:4200/campsiteget");
    }
    @Bean
    public WebMvcConfigurer cordConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
                //WebMvcConfigurer.super.addCorsMappings(registry);
            }
        };
    }
}
