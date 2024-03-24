package web;


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
import java.util.ResourceBundle;

@WebServlet(name = "CreateAccount",value = "/acc/*")
public class CreateAccount extends HttpServlet {
    Logger logger;
    ResourceBundle resourceBundle;
    UserServices userServices;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(CreateAccount.class);
    }


    //Using POST to create a new account and saving
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            Gson gson = new Gson();
            User user = gson.fromJson(req.getReader(), User.class);
            userServices.callSave(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(resourceBundle.getString("user.saved"));
        } catch (NumberFormatException numberFormatException) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(numberFormatException);
        } catch (UserException userException) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(resourceBundle.getString("user.not.found"));
        }
    }
}
