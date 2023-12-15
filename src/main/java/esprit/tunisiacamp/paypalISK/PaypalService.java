/*
package esprit.tunisiacamp.paypalISK;


import com.itextpdf.text.DocumentException;
import com.paypal.api.payments.*;
import com.paypal.api.payments.Payment.*;
import com.paypal.base.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;
import esprit.tunisiacamp.repositeries.TransactionRepositery;
import esprit.tunisiacamp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService  {
    @Autowired
    private APIContext apiContext;
    @Autowired
    private TransactionService transactionService;

    public Payment createPayment(double total, String currency, String method, String intent,
                                 String cancelUrl, String successUrl) throws PayPalRESTException, com.paypal.base.rest.PayPalRESTException {

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


        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException, com.paypal.base.rest.PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
    // *****************************
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    @Autowired
    private TransactionRepositery transactionRepositery;

    public RedirectView payment(@RequestBody esprit.tunisiacamp.entities.shopping.Transaction transaction) {
        //
        System.out.println(transaction);
        try {
            Payment payment = createPayment((double)transaction.getPrice(), "USD", "paypal",
                    "SALE", "http://localhost:1111/rest/" + CANCEL_URL,
                    "http://localhost:1111/rest/" + SUCCESS_URL);

            System.out.println("dd");
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    System.out.println("approved before set ");
                    transaction.setPaid(true);
                    transactionService.updateTransaction(transaction);
                    System.out.println("updated");
                    transactionService.invoiceOfTransaction(transaction);
                    return new RedirectView(link.getHref());
                }
            }

        } catch (PayPalRESTException | com.paypal.base.rest.PayPalRESTException e) {

            e.printStackTrace();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("http://localhost:1111/rest/cancel");
    }



}*/
