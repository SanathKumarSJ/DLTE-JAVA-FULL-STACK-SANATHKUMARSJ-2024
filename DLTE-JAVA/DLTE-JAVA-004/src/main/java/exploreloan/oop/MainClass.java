package exploreloan.oop;

import java.util.Date;
import java.util.Scanner;

public class MainClass implements MyBank {
    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        mainClass.initialize();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new loan");
            System.out.println("2. List available loans");
            System.out.println("3. List closed loans");
            System.out.println("4. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    //Adding new loan
                    mainClass.addLoan();
                    break;
                case 2:
                    // Display available loans
                    mainClass.checkAvailableLoan();
                    break;
                case 3:
                    // Checking closed loan
                    mainClass.getCheckClosedLoan();
                    break;
                case 4:
                    System.out.println("Exiting. Have a great day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public LoanInfo readInput() {
        Scanner scanner = new Scanner(System.in);
        LoanInfo loanInfo = new LoanInfo();
        System.out.println("Thanks for choosing MyBank\nFor creating new account please provide the following details");
        System.out.println("Enter the loan amount");
        loanInfo.setLoanAmount(scanner.nextDouble());
        System.out.println("Enter the contact number");
        loanInfo.setBorrowerContact(scanner.nextLong());
        System.out.println("Enter the loan number");
        loanInfo.setLoanNumber(scanner.nextLong());
        System.out.println("Enter borrower name");
        loanInfo.setBorrowerName(scanner.next());
        System.out.println("Enter the date");
        loanInfo.setLoanDate(Date.parse(scanner.next()));
        System.out.println("Status");
        loanInfo.setLoanStatus(scanner.next());
        return loanInfo;

    }
    @Override
    public void addLoan() {
        for (int index = 4; index < loanInfo.length; index++) {
            loanInfo[index] = readInput();
            break;
        }
    }

    @Override
    public void checkAvailableLoan() {
        for (LoanInfo each : loanInfo) {
            if(each!=null)
            System.out.println(each);
        }

    }

    @Override
    public void getCheckClosedLoan() {
        for (int index = 0; index < loanInfo.length; index++) {
            if (loanInfo[index]!=null && loanInfo[index].getLoanStatus().equals("close")) {
                System.out.println("The loan number " + loanInfo[index].getLoanNumber() + "of " + loanInfo[index].getBorrowerName() + " is closed");
            }

        }


    }
}

