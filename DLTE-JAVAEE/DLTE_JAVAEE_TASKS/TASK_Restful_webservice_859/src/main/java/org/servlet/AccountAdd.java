package org.servlet;


import org.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/add")
public class AccountAdd extends HttpServlet {
    Logger logger;
    ResourceBundle resourceBundle;
    UserServices userServices;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(org.webrest.CreateAccount.class);
    }


    //Using POST to create a new account and saving
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        Long contact = Long.parseLong(req.getParameter("contact"));
        Double balance = Double.parseDouble(req.getParameter("balance"));

        User user = new User(username, password, address, email, contact, balance);
        System.out.println(user.getUsername()+user.getPassword()+user.getContact()+user.getEmail()+user.getBalance()+user.getAddress());
        RequestDispatcher dispatcher = req.getRequestDispatcher("addAccount.jsp");

        try {
            userServices.callSave(user);
            req.setAttribute("info", resourceBundle.getString("user.saved"));
        } catch (NumberFormatException numberFormatException) {
            req.setAttribute("error", numberFormatException);
        } catch (UserException creditCardException) {
            req.setAttribute("error", resourceBundle.getString("user.not.found"));
        }
        dispatcher.forward(req, resp);
    }

    }