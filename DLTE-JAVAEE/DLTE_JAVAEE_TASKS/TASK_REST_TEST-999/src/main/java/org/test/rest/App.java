package org.test.rest;

import org.database.DatabaseTarget;
import org.database.StorageTarget;
import org.database.Transaction;
import org.database.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/transactions")
public class App extends HttpServlet {

    public StorageTarget storageTarget;
    public UserServices userServices;

    @Override
    public void init() {

        storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //DatabaseTarget databaseTarget=new StorageTarget();
        resp.setContentType("application/json");

        // if username and date input execute find all transaction by username and date
        if (req.getParameter("username") != null && req.getParameter("date") != null) {
            List<Transaction> transactions = new ArrayList<>();
            transactions = userServices.callFindByDateAndUsername(String.valueOf(req.getParameter("username")), Date.valueOf(req.getParameter("date")));
            // System.out.println(transactions.size());
            for (int index = 0; index < transactions.size(); index++) {
                resp.getWriter().println(transactions.get(index));
            }

            // if username input execute find all transaction by username
        } else if (req.getParameter("username") != null) {
            List<Transaction> transactions = new ArrayList<>();
            transactions = userServices.callFindByUsername(String.valueOf(req.getParameter("username")));
            resp.getWriter().println(transactions);


            // if no input execute find all transaction
        } else {
            List<Transaction> transactions = new ArrayList<>();
            transactions = userServices.callFindAllTransaction();
            resp.getWriter().println(transactions);

        }
    }
}
