package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="BasicInteraction",value="/second/*")
public class Interaction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int principle=Integer.parseInt(req.getParameter("principle"));
        int tenure=Integer.parseInt(req.getParameter("tenure"));
        double repayment=0.0, EMI=0.0;
        if(principle>=100000&& principle<=300000){
            repayment=principle+(principle*0.24);
        }
        else if(principle>300000&& principle<=500000){
            repayment=principle+(principle*0.25);
        }
        else {
            repayment=principle+(principle*0.29);
        }
        //JSON
        EMI=repayment/tenure;

        resp.setContentType("application/json");

        //JSON--GSON--JAVA
        Gson gson=new Gson();
        String message=gson.toJson(EMI);

        //getwriter method get the string
        resp.getWriter().println(message);

    }
}
