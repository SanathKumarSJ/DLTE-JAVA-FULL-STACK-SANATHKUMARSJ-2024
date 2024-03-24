package web;



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
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

@WebServlet(name="MainServletName",value = "/findname/*")
public class FindByUsername extends HttpServlet {
    public UserServices userService;
    private Logger logger;
    private ResourceBundle resourceBundle;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        resp.setContentType("application/json");
        try {
            logger.info(resourceBundle.getString("user.ok"));
            User user=userService.callFindById(username);
            Gson gson=new Gson();
            String result=gson.toJson(user);
            resp.getWriter().println(result);
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
