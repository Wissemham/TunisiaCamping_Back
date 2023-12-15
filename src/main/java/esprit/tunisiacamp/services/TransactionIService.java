package esprit.tunisiacamp.services;

import com.itextpdf.text.DocumentException;
import esprit.tunisiacamp.entities.shopping.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TransactionIService {
    Transaction addTransaction(Transaction transaction,Integer idShopper,long idTool);
    void deleteTransaction(Transaction transaction);
    void invoiceOfTransaction(Transaction transaction) throws DocumentException, IOException;
    Transaction updateTransaction(Transaction transaction);
    List<Transaction> getMyTransactions(long my_id);
    Transaction cancelTransactionUnpaidDelivery(Transaction transaction);
}
