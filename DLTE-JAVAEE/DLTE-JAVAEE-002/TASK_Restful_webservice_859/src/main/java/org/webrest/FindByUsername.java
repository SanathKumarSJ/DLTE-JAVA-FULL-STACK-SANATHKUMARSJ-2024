package org.webrest;


import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.utils.res.XResourceBundle;
import org.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

@WebServlet(name="MainServletName",value = "/findname/*")
public class FindByUsername extends HttpServlet {
    UserServices userService;
    private Logger logger;
    private ResourceBundle resourceBundle;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        resp.setContentType("application/html");
        try {
            logger.info(resourceBundle.getString("user.ok"));
            User user=userService.callFindById(username);
            Gson gson=new Gson();
            String result=gson.toJson(user);
            resp.getWriter().println(result);


            PrintWriter out = resp.getWriter();

            //  HTML response
            out.println("<html>");
            out.println("<head><title>User_Account</title></head>");
            out.println("<body>");
            out.println("<h2>User Details</h2>");
            out.println("<table border='2'>");
            out.println("<tr><th>Name</th><th>Balance</th><th>Address</th><th>Email</th><th>Password</th><th>Contact</th></tr>");
            out.println("<tr>");
            out.println("<td>" + user.getUsername()+ "</td>");
            out.println("<td>" + user.getBalance()+ "</td>");
            out.println("<td>" + user.getAddress() + "</td>");
            out.println("<td>" + user.getEmail() + "</td>");
            out.println("<td>" + user.getPassword() + "</td>");
            out.println("<td>" + user.getContact() + "</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");


        } catch (UserException userexceptoin) {
            throw new UserException();
        }
    }

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        userService=new UserServices(storageTarget);
        logger= LoggerFactory.getLogger(FindByUsername.class);
        resourceBundle=ResourceBundle.getBundle("servlet");
    }
}
