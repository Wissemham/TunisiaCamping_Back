/*package esprit.tunisiacamp.paypalISK;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.core.rest.PayPalRESTException;
import esprit.tunisiacamp.entities.shopping.Transaction;
import esprit.tunisiacamp.paypal.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.Console;
@CrossOrigin(origins = "*")
@RestController
public class PaypalController {
    @Autowired
    PaypalService service;


    public static final String SUCCESS_URL = "http://localhost:1111/pay/success";
    public static final String CANCEL_URL = "http://localhost:1111/pay/cancel";

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/pay")
    public RedirectView payment(@RequestBody Transaction transaction) {
        System.out.println("approved");
        System.out.println(transaction);
        return service.payment(transaction);
       /* try {
            System.out.println("approved");
            System.out.println(transaction);
            Payment payment = service.createPayment((double) transaction.getPrice(), "USD", "paypal",
                    "sale", "description sample", CANCEL_URL, SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return ResponseEntity.ok(new RedirectView(link.getHref()));
                }
            }
        } catch (PayPalRESTException | com.paypal.base.rest.PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error creating payment");
        }
        return ResponseEntity.badRequest().body("Cannot find approval URL");*/
  /*  }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CANCEL_URL)
    public RedirectView cancelPay() {
        return new RedirectView("http://localhost:1111/rest/cancel");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {

        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {

                return (" success");
            }
        } catch (PayPalRESTException | com.paypal.base.rest.PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return ("cancel");
        /*try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException | com.paypal.base.rest.PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";*/
    //}

    //  1

    /*
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/hme")
    public String home() {
        return  "home";
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/pay")
    public RedirectView payment(@RequestBody Transaction transaction) {
       return service.payment(transaction);
        /*try {
            Payment payment = service.createPayment((double)transaction.getPrice(), "USD", "paypal",
                    "SALE", " description sample ", "http://localhost:1111/" + CANCEL_URL,
                    "http://localhost:1111/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException | com.paypal.base.rest.PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";*/
    /*
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CANCEL_URL)
    public RedirectView cancelPay() {
        return new RedirectView("http://localhost:1111/rest/cancel");
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException | com.paypal.base.rest.PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }*/

    // 2


//}

