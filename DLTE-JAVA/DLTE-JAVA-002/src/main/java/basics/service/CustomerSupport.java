package basics.service;

import javax.xml.crypto.Data;
import java.util.*;

public class CustomerSupport {
    public static void main(String[] args) {
//        CreditCard[] myBank=new CreditCard[10];

        CreditCard[] myBank = {

                new CreditCard(8765678765678L, "Razak Mohamed S", new Date(2034, 12, 30), 555, 100000, new Date(2024, 3, 11), new Date(2024, 3, 30), 1111),
                new CreditCard(2343234345445L, "Shreyas", new Date(2029, 1, 4), 134, 50000, new Date(2024, 3, 5), new Date(2024, 3, 22), 9999),
                new CreditCard(8765678764545L, "Sanath", new Date(2031, 5, 15), 955, 100000, new Date(2024, 5, 5), new Date(2024, 3, 11), 9864),
                new CreditCard(1234565456767L, "Akash", new Date(2028, 8, 11), 767, 10000, new Date(2024, 3, 18), new Date(2024, 3, 29), 1945),
        };
        Scanner scanner = new Scanner(System.in);
        CustomerSupport support = new CustomerSupport();
        support.list(myBank);
        support.sortingCustomer(myBank);
        System.out.println("After sorting");
        support.list(myBank);
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
}