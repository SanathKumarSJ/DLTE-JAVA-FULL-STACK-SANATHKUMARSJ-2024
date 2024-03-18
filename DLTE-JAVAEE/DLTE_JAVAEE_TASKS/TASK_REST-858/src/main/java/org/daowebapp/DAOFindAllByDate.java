package org.daowebapp;

import com.google.gson.Gson;
import org.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.calendar.LocalGregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

@WebServlet(name = "findbydate",value = "/finddate/*")
public class DAOFindAllByDate extends HttpServlet {
    Logger logger;
    ResourceBundle resourceBundle;
    UserServices userServices;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String username = req.getParameter("name");
        String date = req.getParameter("date");
        try {
//            List<Transaction> transactionList = userServices.callFindByDateAndUsername(username, java.sql.Date.valueOf(date));
//            System.out.println(transactionList);
//            Date date1=Date.valueOf(date);
            List<Transaction> transactions=userServices.callFindByDateAndUsername(username, Date.valueOf(date));
            for(Transaction each:transactions){
                System.out.println(each);
            }
            Gson gson = new Gson();
            for(Transaction each:transactions){
                String responseData = gson.toJson(each);
                resp.getWriter().println(responseData);
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (UserException e) {
            throw new UserException();
        }
    }


    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        userServices=new UserServices(storageTarget);
        logger= LoggerFactory.getLogger(DAOFindAllByDate.class);
        resourceBundle=ResourceBundle.getBundle("servlet");

    }
}
