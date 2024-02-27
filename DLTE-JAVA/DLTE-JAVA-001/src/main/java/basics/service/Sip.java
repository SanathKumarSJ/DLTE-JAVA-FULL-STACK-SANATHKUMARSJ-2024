package basics.service;
import java.util.*;
public class Sip {
    public static void main(String[] args) {
        double  monthlyInvestment, expectedReturnsRate, timeperiod;
        double principleAmount, estReturns,totalvalue;
        Scanner scanner=new Scanner(System.in);
        // User monthly investment
        System.out.println("Enter The Monthly investment amount");
        monthlyInvestment= scanner.nextDouble();
        // Expected return interest rate based on this the output is calculated
        System.out.println("Enter the Expected return rate");
        expectedReturnsRate= scanner.nextDouble();
        // Duration in month
        System.out.println("Enter the time period in Months");
        timeperiod= scanner.nextDouble();
        principleAmount= monthlyInvestment*timeperiod;
        estReturns= monthlyInvestment*(((1+(expectedReturnsRate/100.00)*(timeperiod-1))))/expectedReturnsRate*(1+expectedReturnsRate);
        System.out.println("Invested Amount "+principleAmount);
        System.out.println("Est return "+estReturns);
        totalvalue=principleAmount+estReturns;
        System.out.println("Total return "+totalvalue);
    }
}
