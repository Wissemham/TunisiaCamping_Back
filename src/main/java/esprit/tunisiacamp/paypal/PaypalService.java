package esprit.tunisiacamp.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import esprit.tunisiacamp.entities.camping.Reservation;
import esprit.tunisiacamp.repositories.ReservationRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.PayPalRESTException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class PaypalService {
    @Autowired
    private APIContext apiContext;
    @Autowired
    private PaypalConfig payPalConfig;
    @Autowired
    ReservationRepositories reservationRepositories;


    public Payment createPayment(double total, String currency, String method, String intent,
                                 String cancelUrl, String successUrl) throws PayPalRESTException {

        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.valueOf(total));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Payment description");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        //APIContext apiContext = new APIContext(payPalConfig.getClientId(), payPalConfig.getClientSecret(),
          //      payPalConfig.getMode());

        return payment.create(apiContext);
    }
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    public String payment(@RequestBody Order order) {
        Reservation rs= reservationRepositories.findById(8L).get();
        System.out.println(order);
        try {
           /* Payment payment = createPayment(order.getPrice(), "USD", "paypal",
                    "sale","http://localhost:1111/rest/" + CANCEL_URL,
                    "http://localhost:1111/rest/" + SUCCESS_URL);*/
            Payment payment = createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(),"http://localhost:1111/rest/" + CANCEL_URL,
                    "http://localhost:1111/rest/" + SUCCESS_URL);
            System.out.println("dd");
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    System.out.println("dc");
                    rs.setEtat("payer");
                    reservationRepositories.save(rs);
                    System.out.println(link.getHref());
                    return link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "http://localhost:1111/rest/cancel";
    }

}
