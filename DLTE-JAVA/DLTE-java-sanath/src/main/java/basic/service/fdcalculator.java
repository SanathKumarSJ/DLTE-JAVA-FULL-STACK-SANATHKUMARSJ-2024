package basic.service;
import java.util.Scanner;

public class fdcalculator {
    public static void main(String[] args) {
        Long amount=0L,interestrate=0L;
        Integer year=0;
        Float interest,maturityAmount;
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the Principle Amount");
        amount= scanner.nextLong();
        System.out.println("Enter the interest rate");
        interestrate= scanner.nextLong();
        System.out.println("Enter number of years");
        year = scanner.nextInt();
        float rate;
        rate = (float) interestrate/100;
        interest = (float)amount* rate * year;
        maturityAmount = (float)amount+interest;

        System.out.println("\n Interest earner:"+interest+"\n Total return for amount "+amount+" after "+year+" is Rs:"+maturityAmount);
    }
}
