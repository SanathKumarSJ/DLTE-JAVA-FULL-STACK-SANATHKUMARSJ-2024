package org.sip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;


@WebServlet(name="myServlet",value = "/mainservlet/org/*")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        double monthlyInvestment, expectedReturnsRate, timeperiod;
        double principleAmount, estReturns, totalvalue;

        //request for parameter
        String investment = req.getParameter("investment");
        String returnRate = req.getParameter("return");
        String period = req.getParameter("period");

        String salary = req.getParameter("salary");
        String select = req.getParameter("select");
        //Calculate the principle amount if investment, returnrate and period are given else perform tax operation
        if (investment != null && returnRate != null && period != null) {
            monthlyInvestment = Double.parseDouble(investment);
            expectedReturnsRate = Double.parseDouble(returnRate);
            timeperiod = Double.parseDouble(period);
            principleAmount = monthlyInvestment * timeperiod;

            resp.setContentType("application/json");

            estReturns = monthlyInvestment * (((1 + (expectedReturnsRate / 100.00) * (timeperiod - 1)))) / expectedReturnsRate * (1 + expectedReturnsRate);
            totalvalue = principleAmount + estReturns;

            resp.setStatus(HttpServletResponse.SC_OK);

            //Response
            resp.getWriter().println("Principle Amount" + principleAmount);
            resp.getWriter().println("Estimated return " + totalvalue);
        }
        else
            {
            String result = tax(Double.parseDouble(salary), select);
            resp.getWriter().println(result);
        }
    }

    //----------------------------Tax slab---------------------------
    //REQUEST PARAMETER
    public String tax(Double salary, String choice) {
        double newTax, oldTax;
        //RESPONSE
        switch (choice) {
            case "Newregime":
            case "newregime": {
                if (salary <= 250000 && salary >= 0) {
                    return "According to new tax slab no tax should be paid";
                } else if (salary <= 500000 && salary > 200000) {
                    newTax = 0.0;
                    oldTax = salary * (5.0 / 100.00);
                    return "According to new tax slab no tax should be paid that is " + newTax;
                } else if (salary <= 750000 && salary > 500000) {
                    newTax = salary * (10.00 / 100.00);
                    oldTax = salary * (20.00 / 100.00);
                    return "According to new tax slab 10% tax should be paid that is " + newTax;
                } else if (salary <= 1000000 && salary > 750000) {
                    newTax = salary * (15.00 / 100.00);
                    oldTax = salary * (20.00 / 100.00);
                    return "According to new tax slab 15% tax should be paid that is " + newTax;
                } else if (salary <= 1250000 && salary > 1000000) {
                    newTax = salary * (20.00 / 100.00);
                    oldTax = salary * (30.00 / 100.00);
                    return "According to new tax slab 20% tax should be paid that is " + newTax;
                } else {
                    newTax = salary * (30.00 / 100.00);
                    oldTax = salary * (30.0 / 100.00);
                    return "According to new tax slab 30% tax should be paid that is " + newTax;
                }
            }
            case "Oldregime":
            case "oldregime": {
                if (salary <= 250000 && salary >= 0) {
                    return "According to old tax slab no tax should be paid";
                } else if (salary <= 500000 && salary > 200000) {
                    newTax = 0.0;
                    oldTax = salary * (5.0 / 100.00);
                    return "According to old tax slab 5% tax should be paid that is " + oldTax;
                } else if (salary <= 750000 && salary > 500000) {
                    newTax = salary * (10.00 / 100.00);
                    oldTax = salary * (20.00 / 100.00);
                    return "According to new tax slab 20% tax should be paid that is " + oldTax;
                } else if (salary <= 1000000 && salary > 750000) {
                    newTax = salary * (15.00 / 100.00);
                    oldTax = salary * (20.00 / 100.00);
                    return "According to old tax slab 20% tax should be paid that is " + oldTax;
                } else if (salary <= 1250000 && salary > 1000000) {
                    newTax = salary * (20.00 / 100.00);
                    oldTax = salary * (30.00 / 100.00);
                    return "According to old tax slab 30% tax should be paid is " + oldTax;
                } else {
                    newTax = salary * (30.00 / 100.00);
                    oldTax = salary * (30.0 / 100.00);
                    return "According to old tax slab 30% tax should be paid is " + oldTax;
                }

            }
        }
        return "empty";
    }
}
