package basics.service;
import java.util.Date;
import java.util.Scanner;
public class transactionAnalysis {
    public static void main(String[] args) {

        Transaction[] myBank = {
                new Transaction(12500.89, "Maneesh", new Date(2024, 3, 15), "emergency"),
                new Transaction(78625.48, "Rahul", new Date(2024, 6, 3), "family"),
                new Transaction(857895.00, "Ashwin", new Date(2024, 8, 28), "education"),
                new Transaction(1784521.00, "Rohith", new Date(2024, 11, 8), "bill"),
                new Transaction(12575.89, "Vibha", new Date(2024, 8, 21), "emergency"),
        };
        Scanner scanner = new Scanner(System.in);
        // creating object
        transactionAnalysis analyse = new transactionAnalysis();
        //finding transaction in the date range
        analyse.rangeDate(myBank, new Date(2024, 2, 5), new Date(2024, 12, 29));
        // to find least amount
        analyse.leastAmount(myBank);

        // to find highest amount transferred
        analyse.highestAmount(myBank);

        // for particular beneficiary
        analyse.noOftxnOnbeneficiary(myBank, "education");
        analyse.noOftxnOnbeneficiary(myBank, "emergency");
        analyse.filterBasedOnRemark(myBank, "emergency");

        //Sorting
        System.out.println("\n -----Before Sorting-----");
        analyse.printValue(myBank);
        // beneficiary Decending
        analyse.beneficiaryDecending(myBank);
        System.out.println("\n ------After Sorting------");
        analyse.printValue(myBank);

        //Based on amount in ascending
        System.out.println("\n -----Before Sorting-----");
        analyse.printValue(myBank);
        // beneficiary Decending
        analyse.amountacending(myBank);
        System.out.println("\n ------After Sorting------");
        analyse.printValue(myBank);
    }

    public void rangeDate(Transaction[] array, Date start, Date end) {
        System.out.println("Transactions made in between " + start.getDate() + " and " + end.getDate());
        for (Transaction each : array) {
            if (each.getDateOfTransaction().getDate() >= start.getDate() && each.getDateOfTransaction().getDate() <= end.getDate()) {
                System.out.println("The amount of Rs " + each.getTransactionAmount() + " was transferred to " + each.getTransactionTo() + " for the purpose of " + each.getRemarks() + "on " + each.getDateOfTransaction());
            }
        }
    }

    public void leastAmount(Transaction[] customer) {
        // using selection sorting finding least amount
        Transaction backup = null;
        for (int select = 0; select < customer.length; select++) {
            for (int next = select + 1; next < customer.length; next++) {
                // swapping the name is not sufficient all the 8 properties needs to me swapped
                if (customer[select].getTransactionAmount().compareTo(customer[next].getTransactionAmount()) > 0) {
                    backup = customer[select];
                    customer[select] = customer[next];
                    customer[next] = backup;
                }
            }
        }
        System.out.println("The least amount transferred is " + customer[0].getTransactionAmount());
    }

    public void highestAmount(Transaction[] customer) {
        // using selection sorting finding highest amount
        Transaction backup = null;
        for (int select = 0; select < customer.length; select++) {
            for (int next = select + 1; next < customer.length; next++) {
                // swapping the name is not sufficient all the 8 properties needs to me swapped
                if (customer[select].getTransactionAmount().compareTo(customer[next].getTransactionAmount()) > 0) {
                    backup = customer[select];
                    customer[select] = customer[next];
                    customer[next] = backup;
                }
            }
        }
        System.out.println("The Maximum amount transferred is " + customer[customer.length - 1].getTransactionAmount());
    }

    public void noOftxnOnbeneficiary(Transaction[] array, String beneficiary) {
        int countNoOfTransaction = 0;
        for (Transaction each : array) {
            // comparing the beneficiary with the data
            if (each.getRemarks().equals(beneficiary)) {
                countNoOfTransaction += 1;
            }
        }
        System.out.println("Based on " + beneficiary + " " + countNoOfTransaction + " transactions performed");
    }

    public void filterBasedOnRemark(Transaction[] array, String beneficiary) {
        for (Transaction each : array) {
            // comparing the beneficiary with the data
            if (each.getRemarks().equals(beneficiary)) {
                System.out.println("On " + each.getRemarks() + " beneficiary the following transactions are made");
                // for printing the transaction another for loop is used
                for (Transaction each1 : array) {
                    if (each1.getRemarks().equals(beneficiary)) {
                        System.out.println("Transaction amount of " + each1.getTransactionAmount() + " is tranferred to " + each1.getTransactionTo() + " on " + each1.getDateOfTransaction());
                    }
                }
                break; // two avoid duplicate printing of same beneficiary
            }
        }
    }

    // -----------------Sorting Beneficiary------------------

    // printing the data
    public void printValue(Transaction[] array) {
        for (Transaction each : array) {
//            System.out.println("\n");
            System.out.println(each);
        }

    }

    public void beneficiaryDecending(Transaction[] array) {
        // sorting the beneficiary
        Transaction backup = null;
        for (int select = 0; select < array.length; select++) {
            for (int next = 0; next < array.length - next - 1; next++) {
                // swapping the name
                if (array[next + 1].getRemarks().compareTo(array[next].getRemarks()) > 0) {
                    backup = array[next];
                    array[next] = array[next + 1];
                    array[next + 1] = backup;
                }
            }
        }
    }

    //--------amount ascending---------

    public void amountacending(Transaction[] array) {
        Transaction backup = null;
        for (int select = 0; select < array.length; select++) {
            for (int next = 0; next < array.length - next - 1; next++) {
                // swapping the name
                if (array[next + 1].getTransactionAmount().compareTo(array[next].getTransactionAmount()) < 0) {
                    backup = array[next];
                    array[next] = array[next + 1];
                    array[next + 1] = backup;
                }
            }
        }
    }
}