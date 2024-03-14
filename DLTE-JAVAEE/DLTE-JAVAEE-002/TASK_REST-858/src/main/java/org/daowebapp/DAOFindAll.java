package org.daowebapp;

import com.google.gson.Gson;
import org.database.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="MainServlet",value = "/findall/*")
public class DAOFindAll extends HttpServlet {
    UserServices userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<User> userList=userService.callFindAll();
        Gson gson=new Gson();
        String result=gson.toJson(userList);
        resp.getWriter().println(result);
    }

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userService = new UserServices(storageTarget);
    }
}