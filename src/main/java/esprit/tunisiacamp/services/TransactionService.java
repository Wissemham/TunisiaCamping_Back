package esprit.tunisiacamp.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.entities.shopping.Transaction;
import esprit.tunisiacamp.repositeries.ToolRepositery;
//import esprit.tunisiacamp.repositeries.TransactionRepositery;
//import esprit.tunisiacamp.repositeries.UserRepositery;
import esprit.tunisiacamp.repositories.TransactionRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Service
public class TransactionService implements TransactionIService {
    @Autowired
    //TransactionRepositery tr_repo;
    TransactionRepository tr_repo;
    @Autowired
    ToolRepositery to_repo;
    @Autowired
    UserRepository us_repo;

    @Override
    public Transaction addTransaction(Transaction transaction,Integer idShopper,long idTool) {
        User shopper=us_repo.findById(idShopper).get();
        transaction.setShopper(shopper);
        Tool tool=to_repo.findById(idTool).get();
        if(tool.getStock()>0)
             tool.setStock(tool.getStock()-1);
        to_repo.save(tool);
        transaction.setTool(tool);
        return tr_repo.save(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        tr_repo.delete(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return tr_repo.save(transaction);
    }

    @Override
    public List<Transaction> getMyTransactions(long my_id) {
        List<Transaction> output = new ArrayList<Transaction>();
        for (Transaction tr:tr_repo.findAll()) {
            //if (tr.getShopper().getIdUser()==my_id)
                output.add(tr);
        }
        return output;
    }

    @Override
    public Transaction cancelTransactionUnpaidDelivery(Transaction transaction) {
        if(tr_repo.findById(transaction.getIdTransaction()).get().getCreation()
                .before(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                && !transaction.getPaid())
            deleteTransaction(transaction);
        return transaction;
    }

    @Override
    public void invoiceOfTransaction(Transaction transaction) throws DocumentException, IOException {

            Document document = new Document();
            PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("invoice.pdf")));
            document.open();
            // Add invoice header
            Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.BLUE);
            Paragraph header = new Paragraph("Invoice",font);
            document.add(header);
            // Add transaction details
            String content_transaction= "\n\n" +"Transaction Date : " + transaction.getCreation().toString() + "\n\n"
                    + "payment : " + transaction.getPaid().toString() + "\n\n"
                    + "shipment : " + transaction.getFor_shipment().toString() + "\n\n";
            Chunk chunk1 = new Chunk(content_transaction, font);
            document.add(chunk1);
           // Add tool details
            Tool tool=to_repo.findById(transaction.getTool().getIdTool()).get();
            String content_tool= "\n\n" +"Tool name : " + tool.getName() + "\n\n"
                    + "price : " + tool.getPrice() + "\n\n"
                    + "type : " + tool.getType() + "\n\n";
            Chunk chunk2 = new Chunk(content_tool, font);
            document.add(chunk2);
            document.close();

    }
}
