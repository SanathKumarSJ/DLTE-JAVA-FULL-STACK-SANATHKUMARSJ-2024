package basics.service;
import java.util.*;
public class sip {
    public static void main(String[] args) {
        double  monthlyInvestment, expectedReturnsrate, timeperiod;
        double principleAmount, estRetuens,totalvalue;
        Scanner scanner=new Scanner(System.in);
        // User monthly investment
        System.out.println("Enter The Monthly investment amount");
        monthlyInvestment= scanner.nextDouble();
        // Expected return interest rate based on this the output is calculated
        System.out.println("Enter the Expected return rate");
        expectedReturnsrate= scanner.nextDouble();
        // Duration in month
        System.out.println("Enter the time period in Months");
        timeperiod= scanner.nextDouble();
        principleAmount= monthlyInvestment*timeperiod;
        estRetuens= monthlyInvestment*(((1+(expectedReturnsrate/100.00)*(timeperiod-1))))/expectedReturnsrate*(1+expectedReturnsrate);
        System.out.println("Invested Amount "+principleAmount);
        System.out.println("Est return "+estRetuens);
        totalvalue=principleAmount+estRetuens;
        System.out.println("Total return "+totalvalue);
    }
}
