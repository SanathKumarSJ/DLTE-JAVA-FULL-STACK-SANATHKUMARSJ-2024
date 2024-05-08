package first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@WebServlet("/first/api/*") -->also valid
@WebServlet(name="MySecondServlet",value = "/first/api/") //*-> capable to work with doget dopost and also some keyword can be added after API
// for example --> /first/api/*-- /first/api/Acc1 or /first/api/Acc2 etc.., can get called

public class MyFirstServlet extends HttpServlet {
    Logger logger;

    @Override
    public void destroy() {
        //nothing inside destroy will get executed
        logger.info("Servlet get destroyed");
    }

    @Override
    public void init() throws ServletException {
        logger=LoggerFactory.getLogger(MyFirstServlet.class);
        logger.info("Servlet has Initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        logger.info("Servlet GET has executed");
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            logger.info("Servlet GET has executed");
        } else {
//            logger.info("Received" +req.getPathInfo("lumpsum");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path.equals("/post")) {
            logger.info("Servlet POST has executed");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        logger.info("Servlet PUT has executed");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        logger.info("Servlet DELETE has executed");
    }
}
