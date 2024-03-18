package transaction;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;


@WebServlet(name = "Transaction", value = "/trxn/*")
public class TransactionAnalysis  extends HttpServlet {
    List<Transaction> transactionArrayList = Stream.of(new Transaction(7845,"sanath","vibha",52000.00,new Date(2024,12,11)),
            new Transaction(7745,"rohith","naresh",72000.00,new Date(2024,12,10)),
            new Transaction(4845,"mahesh","varun",22000.00,new Date(2024,12,19)),
            new Transaction(1845,"kumar","neeraj",92000.00,new Date(2024,12,17))).collect(Collectors.toList());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
//        resp.setContentType("application/json");
//        String json = gson.toJson(transactionArrayList);
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.getWriter().println(json);
        //---------------Filtering-------------------------

        String max = req.getParameter("max");
        String min = req.getParameter("min");

            //if max and min parameter are included then filtering operation performs
        if (min != null && max != null) {
            double maxAmount = Double.parseDouble(req.getParameter("max"));
            double minAmount = Double.parseDouble(req.getParameter("min"));

            // filtering logic
            List<Transaction> filter = transactionArrayList.stream().filter(each -> each.getTransactionAmount() >= (minAmount) && each.getTransactionAmount() <= (maxAmount)).collect(Collectors.toCollection(ArrayList::new));
            //response
            for (Transaction each : filter) {
                resp.getWriter().println(gson.toJson(each));
            }
        }
        else {
// display all
            resp.setContentType("application/json");
            String json = gson.toJson(transactionArrayList);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("POST EXECUTED");
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactionArrayList.add(transaction);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(transaction.getTransactionId()+" from "+transaction.getTransactionFrom()+" has added to the records");




    }
}
