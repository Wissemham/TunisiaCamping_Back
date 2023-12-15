package esprit.tunisiacamp.restControllers;

import com.itextpdf.text.DocumentException;
import esprit.tunisiacamp.entities.shopping.Transaction;
import esprit.tunisiacamp.services.TransactionIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TransactionRestController {
    @Autowired
    TransactionIService transactionservice;

    @PostMapping("/transaction/addtransaction")
    public Transaction addTransaction(@RequestBody Transaction transaction,@RequestParam Integer idShopper,@RequestParam long idTool){
        return transactionservice.addTransaction(transaction,idShopper,idTool);}
    @DeleteMapping("/transaction/deletetransaction")
    public void deleteTransaction(@RequestBody Transaction transaction){transactionservice.deleteTransaction(transaction);}

    @PutMapping("/transaction/updatetransaction")
    public Transaction updateTransaction(@RequestBody Transaction transaction){return transactionservice.updateTransaction(transaction);}

    @GetMapping("/transaction/getmytransactions")
    public List<Transaction> getMyTransaction(@RequestParam Integer my_id){return transactionservice.getMyTransactions(my_id);}
    @GetMapping("/transaction/canceltransactiondeliveryunpaid")
    public Transaction cancelTransactionUnpaidDelivery(@RequestBody Transaction transaction){return transactionservice.cancelTransactionUnpaidDelivery(transaction);}

    @PostMapping("/transaction/invoiceoftransaction")
    public void invoiceOfTransaction(@RequestBody Transaction transaction) throws DocumentException, IOException
        { transactionservice.invoiceOfTransaction(transaction);}

}
