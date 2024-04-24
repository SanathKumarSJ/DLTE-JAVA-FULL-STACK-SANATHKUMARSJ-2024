package org.servlet;

import org.database.DatabaseTarget;
import org.database.StorageTarget;
import org.database.Transaction;
import org.database.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrest.FindAllTransaction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;


@WebServlet("/viewByUsername")
public class ViewTransactionByName extends HttpServlet {

    UserServices userService;
    private Logger logger;
    private ResourceBundle resourceBundle;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userService = new UserServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(FindAllTransaction.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        List<Transaction> transactions = userService.callFindByUsername(username);
        System.out.println(username);
        System.out.println(transactions.get(0).getTransactionDoneBy());
        RequestDispatcher dispatcher = req.getRequestDispatcher("viewByUsername.jsp");
        req.setAttribute("myList", transactions);
        dispatcher.include(req, resp);
    }
}