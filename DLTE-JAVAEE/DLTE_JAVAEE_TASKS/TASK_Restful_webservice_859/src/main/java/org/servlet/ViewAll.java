package org.servlet;

import com.google.gson.Gson;
import org.database.*;
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
import java.util.MissingResourceException;
import java.util.ResourceBundle;


@WebServlet("/view")
public class ViewAll extends HttpServlet {

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
        List<Transaction> transactions = userService.callFindAllTransaction();
        RequestDispatcher dispatcher = req.getRequestDispatcher("viewAll.jsp");
        req.setAttribute("myTransaction", transactions);
        dispatcher.include(req, resp);
    }
}