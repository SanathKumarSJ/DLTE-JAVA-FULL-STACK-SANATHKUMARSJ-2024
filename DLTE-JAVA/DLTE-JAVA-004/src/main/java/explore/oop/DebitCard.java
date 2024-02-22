package explore.oop;
import java.util.Scanner;

public class DebitCard extends Account {
    Long CardNumber;
    Integer CardPin;
    Scanner scanner = new Scanner(System.in);

    public DebitCard(Long accountNumber, String accountHolder, Double accountBalance, Long cardNumber, Integer cardPin) {
        super(accountNumber, accountHolder, accountBalance);
        CardNumber = cardNumber;
        CardPin = cardPin;

    }

    public Long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        CardNumber = cardNumber;
    }

    public Integer getCardPin() {
        return CardPin;
    }

    public void setCardPin(Integer cardPin) {
        CardPin = cardPin;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }


    //check amount to be received as parameter is less than balance to approve withdraw
    //approve withdraw only if pin entered at running time is same as cardPin

    public void amountWithdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Credit Card pin");
        Integer pin = scanner.nextInt();
        if (pin.equals(getCardPin())) {
            System.out.println("Enter the withdrawal Amount");
            Double withDrawAmount = scanner.nextDouble();
            Double remBalance = getAccountBalance() - withDrawAmount;
            if (withDrawAmount <= getAccountBalance()) {
                System.out.println("Sufficient Amount Exists");
                System.out.println("The Amount Withdrawn Successfully");
                System.out.println("Remaining Balance " + remBalance);
            }
            else {
                System.out.println("Insufficient Balance \nBalance "+getAccountBalance());
            }

        } else {
            System.out.println("Wrong Credit Card PIN");
        }
    }
}