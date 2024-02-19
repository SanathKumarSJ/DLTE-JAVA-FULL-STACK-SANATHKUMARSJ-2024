package basics.service;
import java.util.*;
public class noofdebits {
    public static void main(String[] args) {
        //txn refers to the crediting or debiting in the main account and mainbalance is the primary balance
        double txn, mainbalance, currentbal;
        int number = 10, n = 1, debit = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Balance");
        mainbalance = scanner.nextDouble();
        while (number != 0) {
            number -= 1;
            // new balance increases if money credited or decreases if money debited
            System.out.println("Enter the new balance amount " + n);
            n += 1;
            currentbal = scanner.nextDouble();
            if (currentbal < mainbalance) {
                debit += 1;
                mainbalance = currentbal;

            }
            mainbalance= currentbal;
        }System.out.println("The total number of debit transaction is " + debit);
    }
}
