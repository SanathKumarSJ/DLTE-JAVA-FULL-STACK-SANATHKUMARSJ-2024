package org.webrest;


import com.google.gson.Gson;
import org.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@WebServlet(name = "FindAllByName", value = "/findtxn/*")
public class FindAllTransaction extends HttpServlet {
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


    //Getting list of transactions based on username
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        resp.setContentType("application/json");
        try {
            System.out.println(username);
            List<Transaction> transactions = userService.callFindByUsername(username);
            Gson gson = new Gson();
            String responseData = gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(responseData);
        }
        catch (UserException | MissingResourceException userException){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println(resourceBundle.getString("user.not.found"));
        }
    }

}