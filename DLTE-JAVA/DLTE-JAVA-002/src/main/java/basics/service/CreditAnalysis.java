package basics.service;

import java.util.Date;
import java.util.Scanner;

public class CreditAnalysis {
    public static void main(String[] args) {
//        CreditCard[] myBank=new CreditCard[10];

        CreditCard[] myBank = {

                new CreditCard(1254789545251L, "Rohith", new Date(2034, 12, 30), 555, 100000, new Date(2024, 3, 11), new Date(2024, 3, 30), 1111),
                new CreditCard(5689562345656L, "Shreyas", new Date(2029, 1, 4), 134, 50000, new Date(2024, 3, 5), new Date(2024, 3, 22), 9999),
                new CreditCard(7451252330222L, "Sanath", new Date(2031, 5, 15), 955, 100000, new Date(2024, 5, 5), new Date(2024, 3, 11), 9864),
                new CreditCard(9854785122212L, "Virat", new Date(2028, 8, 11), 767, 10000, new Date(2024, 3, 18), new Date(2024, 3, 29), 1945),
        };
        Scanner scanner = new Scanner(System.in);
        CreditAnalysis analysis = new CreditAnalysis();
        analysis.list(myBank);
        analysis.sortingCustomer(myBank);
        System.out.println("After sorting");
        analysis.list(myBank);
        analysis.filterLimit(myBank, 50000, 100000);// calling filter limit method

        analysis.filterdateofpayment(myBank, new Date(2024, 3, 11), new Date(2024, 3, 29));

        analysis.pinUpdate(myBank);

        analysis.updateLimit(myBank, 5, 50.00); //specified the date of billing which the limit needs to be updated

    }

    public void findBillDate(CreditCard[] customers, Date start, Date end) {
        System.out.println("Customers those having bill dates between " + start.getDate() + " and " + end.getDate());
        for (CreditCard each : customers) {
            if (each.getDateOfBillGeneration().getDate() >= start.getDate() && each.getDateOfBillGeneration().getDate() <= end.getDate()) {
                System.out.println(each.getCreditCardHolder() + " " + each.getDateOfBillGeneration().getDate());
            }
        }
    }


    public void list(CreditCard[] customer) {
        for (CreditCard each : customer) {
            System.out.println(each);
        }
    }


    public void sortingCustomer(CreditCard[] customer) {
        // for getting 8 propertiy of credit card not only name
        CreditCard backup = null;
        for (int select = 0; select < customer.length; select++) {
            for (int next = select + 1; next < customer.length; next++) {
                // swapping the name is not sufficient all the 8 properties needs to me swapped
                if (customer[select].getCreditCardHolder().compareTo(customer[next].getCreditCardHolder()) > 0) {
                    backup = customer[select];
                    customer[select] = customer[next];
                    customer[next] = backup;
                }
            }
        }
    }

    public void filterLimit(CreditCard[] limit, int start, int end) {
        for (CreditCard each : limit) {
            if (each.getCreditCardLimit() >= start && each.getCreditCardLimit() <= end) {
                System.out.println("The Credit card Holder " + each.getCreditCardHolder() + " is having the limit within the range of " + start + " & " + end + " \n The limit is " + each.getCreditCardLimit());
            } else {
                System.out.println("The credit card holder " + each.getCreditCardHolder() + " doesn't have credit limit within the range");
            }
        }
    }

    public void filterdateofpayment(CreditCard[] dateofpayment, Date start, Date end) {
        for (CreditCard each : dateofpayment) {
            if (each.getDateOfBillPayment().before(end) && each.getDateOfBillPayment().after(start)) {
                System.out.println("\n The Date of payment ranges from " + start.getDate() + "to " + end.getDate());
                System.out.println("The Credit card holder " + each.getCreditCardHolder() + " has made on-time payment mentioned in the above mentioned range\n The date of payment is: " + each.getDateOfBillPayment().getDate());
            }
        }

    }

    public void pinUpdate(CreditCard[] pin) {
        Scanner scanner = new Scanner(System.in);
        // prompt whether the user needs to update pin ot not
        System.out.println("You want to update the pin specify [yes/no]??");
        String choice = scanner.next();
        if (choice.equals("yes")) {
            System.out.println("Enter the old PIN"); // authenticate the user by reading old password
            Integer enteredPin = scanner.nextInt();
            for (CreditCard each : pin) {
                if (enteredPin.equals(each.getCreditCardPin())) {
                    System.out.println(" " + each.getCreditCardHolder() + " Please enter the new pin"); // user found ask for new pin
                    Integer newPin = scanner.nextInt();
                    System.out.println(" Please re-enter the new pin"); // ask for re-enter
                    Integer reEnteredPin = scanner.nextInt();
                    if (newPin.equals(reEnteredPin)) {
                        // re-entered pin matches --> update
                        System.out.println("Pin updated");
                    } else
                    // no match throw invalid warning
                    {
                        System.out.println("Entered wrong pin");
                    }
                    // Display which user updated the pin
                    System.out.println(" " + each.getCreditCardHolder() + " has successfully change pin from " + each.getCreditCardPin() + " to New PIN XXXX \nNew PIN cannot be disclosed");
                }

            }
        }
    }

    public void updateLimit(CreditCard[] array, int date, double newlim) {
        for (CreditCard each : array) {
            int newdate= each.getDateOfBillGeneration().getDay();
            if (newdate!=date)
            {
                double updatedLimit= (float)each.getCreditCardLimit()*(newlim/100);
                double newlimit= (float)each.getCreditCardLimit()+updatedLimit;
                //System.out.println(updatedLimit);
                System.out.println(each.getCreditCardHolder()+"'s credit limit is updated to "+newlimit+" from "+each.getCreditCardLimit());

            }

        }

    }
}