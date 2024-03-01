package basics.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
public class TransactionAnalysis implements Runnable{
    //

        static Transaction[] myBank = {
                new Transaction(10500.89, "Maneesh", new Date(2024, 3, 15), "emergency"),
                new Transaction(78625.48, "Rahul", new Date(2024, 6, 3), "family"),
                new Transaction(857895.00, "Ashwin", new Date(2024, 8, 28), "education"),
                new Transaction(1784521.00, "Rohith", new Date(2024, 11, 8), "bill"),
                new Transaction(12575.89, "Vibha", new Date(2024, 8, 21), "emergency"),
        };
//
 public static void main(String[] args) {
     TransactionAnalysis analyse = new TransactionAnalysis();
 }

    synchronized public void highestAmount() {
        // using selection sorting finding highest amount
        Transaction backup = null;
        for (int select = 0; select < myBank.length; select++) {
            for (int next = select + 1; next < myBank.length; next++) {
                // swapping the name is not sufficient all the 8 properties needs to me swapped
                if (myBank[select].getTransactionAmount().compareTo(myBank[next].getTransactionAmount()) > 0) {
                    backup = myBank[select];
                    myBank[select] = myBank[next];
                    myBank[next] = backup;
                }
            }
        }
        System.out.println("The maximum amount transferred is " + myBank[myBank.length-1].getTransactionAmount());
    }

    public void rangeDate(){
        String thread4= Thread.currentThread().getName();
        Date start1= new Date(2024, 3, 15);
        Date end= new Date(2024, 8, 28);

        System.out.println("Transactions made in between " + start1.getDate()+ " and " + end.getDate());
        for (Transaction each : myBank) {
            if (each.getDateOfTransaction().getDate() >= start1.getDate()&& each.getDateOfTransaction().getDate() <= end.getDate()) {
                System.out.println("The amount of Rs " + each.getTransactionAmount() + " was transferred to " + each.getTransactionTo() + " for the purpose of " + each.getRemarks() + "on " + each.getDateOfTransaction());
            }
        }
    }


    public void noOftxnOnbeneficiary() {
        Scanner scanner = new Scanner(System.in);
        int countNoOfTransaction = 0;
        System.out.println("Enter the beneficiary");
        String beneficiary= scanner.nextLine();
        for (Transaction each : myBank) {
            // comparing the beneficiary with the data
            if (each.getRemarks().equals(beneficiary)) {
                countNoOfTransaction += 1;
            }
        }
        System.out.println("Based on " + beneficiary + " " + countNoOfTransaction + " transactions performed");
    }

    public void filterBasedOnRemark() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the beneficiary");
        String beneficiary= scanner.nextLine();
        for (Transaction each : myBank) {
            // comparing the beneficiary with the data
            if (each.getRemarks().equals(beneficiary)) {
                System.out.println("On " + each.getRemarks() + " beneficiary the following transactions are made");
                // for printing the transaction another for loop is used
                for (Transaction each1 : myBank) {
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
    public void printValue() {
        for (Transaction each : myBank) {
//            System.out.println("\n");
            System.out.println(each);
        }

    }

    public void beneficiaryDecending() {
        // sorting the beneficiary
        Transaction backup = null;
        for (int select = 0; select < myBank.length; select++) {
            for (int next = 0; next < myBank.length - next - 1; next++) {
                // swapping the name
                if (myBank[next + 1].getRemarks().compareTo(myBank[next].getRemarks()) > 0) {
                    backup = myBank[next];
                    myBank[next] = myBank[next + 1];
                    myBank[next + 1] = backup;
                }
            }
        }
        printValue();
    }

    //--------amount ascending---------

    public void amountacending() {
        System.out.println();
        Transaction backup = null;
        for (int select = 0; select < myBank.length; select++) {
            for (int next = 0; next < myBank.length - next - 1; next++) {
                // swapping the name
                if (myBank[next + 1].getTransactionAmount().compareTo(myBank[next].getTransactionAmount()) < 0) {
                    backup = myBank[next];
                    myBank[next] = myBank[next + 1];
                    myBank[next + 1] = backup;
                }
            }
        }
        printValue();
    }


    @Override
    synchronized public void run() {
//        //Thread.currentThread().getName();
        System.out.println("Choose \n1.To find highest Amount \n 2.To get transaction on given Date range \n3. To get amount in ascending order \n4. To get beneficiary in decending order \n5. To filter based on Remark \n6. To get no.of transaction made on selected beneficiary \n7. To get leastAmount");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        switch (choice){
            case 1:
                highestAmount();
                break;
            case 2:
                rangeDate();
                break;
            case 3:
                amountacending();
                break;
            case 4:
                beneficiaryDecending();
                break;
            case 5:
                filterBasedOnRemark();
                break;
            case 6:
                noOftxnOnbeneficiary();
                break;
            case 7:
                leastAmount();
                break;
        }
    }

    private void main() {
    }

    public void leastAmount() {
        Transaction backup = null;
        for (int select = 0; select < myBank.length; select++) {
            for (int next = select + 1; next < myBank.length; next++) {
                // swapping the name is not sufficient all the 8 properties needs to me swapped
                if (myBank[select].getTransactionAmount().compareTo(myBank[next].getTransactionAmount()) > 0) {
                    backup = myBank[select];
                    myBank[select] = myBank[next];
                    myBank[next] = backup;
                }
            }
        }
        System.out.println("The least amount transferred is " + myBank[0].getTransactionAmount());
    }
}
    //        Scanner scanner = new Scanner(System.in);
//        // creating object
//        System.out.println("!!");

    //finding transaction in the date range
    //analyse.rangeDate(myBank, new Date(2024, 2, 5), new Date(2024, 12, 29));
//        // to find least amount
//        analyse.leastAmount();
//
//        // to find highest amount transferred
//        analyse.highestAmount();
//
//        // for particular beneficiary
//        analyse.noOftxnOnbeneficiary(myBank, "education");
//        analyse.noOftxnOnbeneficiary(myBank, "emergency");
//        analyse.filterBasedOnRemark(myBank, "emergency");
//
//        //Sorting
//        System.out.println("\n -----Before Sorting-----");
//        //analyse.printValue(myBank);
//        // beneficiary Decending
//        analyse.beneficiaryDecending();
//        System.out.println("\n ------After Sorting------");
//        analyse.printValue();
//
//        //Based on amount in ascending
//        System.out.println("\n -----Before Sorting-----");
//        analyse.printValue();
//        // beneficiary Decending
//        analyse.amountacending(myBank);
//        System.out.println("\n ------After Sorting------");
//        analyse.printValue();


//    public void rangeDate(Transaction[] array, Date start, Date end) {
//        Scanner scanner=new Scanner(System.in);
//        String thread4= Thread.currentThread().getName();
////        System.out.println("Enter the starting date");
////        String start = new Date.parse(scanner.next());
////
////        System.out.println("Enter the starting date");
////        String end = new Date.parse(scanner.next());
//
//        System.out.println("Transactions made in between " + start+ " and " + end);
//        for (Transaction each : array) {
//            if (each.getDateOfTransaction().getDate() >= start.getDate() && each.getDateOfTransaction().getDate() <= end.getDate()) {
//                System.out.println("The amount of Rs " + each.getTransactionAmount() + " was transferred to " + each.getTransactionTo() + " for the purpose of " + each.getRemarks() + "on " + each.getDateOfTransaction());
//            }
//        }
//    }