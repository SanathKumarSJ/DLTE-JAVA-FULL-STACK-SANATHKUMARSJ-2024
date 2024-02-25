package basics.service;


import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditCardMain {
    public static void main(String[] args) {
        CreditCard[] creditCards = {new CreditCard(789564556L, "sanath", new Date(2024, 9, 12), 888, 50000, new Date(2024, 04, 01), new Date(2024, 4, 10), 1111),
                new CreditCard(7821121556L, "Tejas", new Date(2024, 12, 10), 788, 100000, new Date(2024, 5, 1), new Date(2024, 5, 10), 1112),
                new CreditCard(787412356L, "Rohith", new Date(2024, 7, 22), 828, 75000, new Date(2024, 6, 1), new Date(2024, 6, 10), 2111),
                new CreditCard(789589898L, "manoj", new Date(2024, 11, 1), 808, 20000, new Date(2024, 7, 1), new Date(2024, 7, 10), 1131)};

        // creating the object
        CreditCardMain creditAnalysis = new CreditCardMain();
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        //ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        System.out.println("Enter the choice 1 for filter on limit & 2 for filter on date");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                try {
                    creditAnalysis.filterLimit(creditCards, 50000, 110000);
                    break;
                } catch (MyCardException creditCardLimit) {
                    logger.log(Level.WARNING, creditCardLimit.toString());
                    creditAnalysis.filterLimit(creditCards, 50000, 110000);
                    break;
                }
            }
            case 2: {
                try {
                    creditAnalysis.filterdateofpayment(creditCards, new Date(2024,4,10), new Date(2024,07,10));
                    break;
                } catch (MyCardException creditCardLimit) {
                    logger.log(Level.WARNING, creditCardLimit.toString());
                    creditAnalysis.filterdateofpayment(creditCards, new Date(2024,4,10), new Date(2024,07,10));
                    break;
                }
            }
        }
    }


    public void filterLimit(CreditCard[] limit, int start, int end) {
        boolean flag=true;
        for (CreditCard each : limit) {
            if (each.getCreditCardLimit() >= start && each.getCreditCardLimit() <= end) {
                System.out.println("The Credit card Holder " + each.getCreditCardHolder() + " is having the limit within the range of " + start + " & " + end + " \n The limit is " + each.getCreditCardLimit());
            }
            else{
                //System.out.println("Exception");
                throw new MyCardException();
            }
        }
        }
    public void filterdateofpayment(CreditCard[] dateofpayment, Date start, Date end) {
        for (CreditCard each : dateofpayment) {
            if (each.getDateOfBillPayment().before(end) && each.getDateOfBillPayment().after(start)) {
                System.out.println("\n The Date of payment ranges from " + start.getDate() + "to " + end.getDate());
                System.out.println("The Credit card holder " + each.getCreditCardHolder() + " has made on-time payment mentioned in the above mentioned range\n The date of payment is: " + each.getDateOfBillPayment().getDate());
            }
            else{
                //System.out.println("Exception block");
                throw new MyCardException();
            }
        }

    }
}