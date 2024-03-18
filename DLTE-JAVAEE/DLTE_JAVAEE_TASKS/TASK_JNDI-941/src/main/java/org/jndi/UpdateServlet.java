package org.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet(value = "/jndi/*")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Context context = new InitialContext();
            Connection connection = null;
            DataSource dataSource= (DataSource) context.lookup("java:/OracleDS");
            connection=dataSource.getConnection();
            // request name from server
            String userName = req.getParameter("name");
            // request balance to be updated from server
            String balance = req.getParameter("balance");
            if (userName != null && balance != null) {
                Double balanceAmount = Double.parseDouble(balance);
                String query = "Update mybank_users set balance=? where username=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDouble(1, balanceAmount);
                preparedStatement.setString(2, userName);
                int result = preparedStatement.executeUpdate();
                if (result != 0)
                    resp.getWriter().println("User Account Information updated");
                else
                    resp.getWriter().println("User Account Information updation failed");
            }
            else{
                resp.getWriter().println("Input Error");
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();

        }
    }
}
