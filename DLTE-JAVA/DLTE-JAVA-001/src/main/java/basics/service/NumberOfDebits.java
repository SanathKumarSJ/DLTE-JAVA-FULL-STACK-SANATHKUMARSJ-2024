package basics.service;
import java.util.*;
public class NumberOfDebits {
    public static void main(String[] args) {
        //txn refers to the crediting or debiting in the main account and mainbalance is the primary balance
        double txn, mainBalance, currentBalance;
        int number = 10, n = 1, debit = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Balance");
        mainBalance = scanner.nextDouble();
        while (number != 0) {
            number -= 1;
            // new balance increases if money credited or decreases if money debited
            System.out.println("Enter the new balance amount " + n);
            n += 1;
            currentBalance = scanner.nextDouble();
            if (currentBalance < mainBalance) {
                debit += 1;
                mainBalance = currentBalance;

            }
            mainBalance= currentBalance;
        }System.out.println("The total number of debit transaction is " + debit);
    }
}
