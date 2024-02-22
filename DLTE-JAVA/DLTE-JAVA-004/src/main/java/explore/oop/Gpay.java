package explore.oop;

import java.util.Scanner;

public class Gpay extends Account {
    String userName;
    Integer upiPin;

    public Gpay(Long accountNumber, String accountHolder, Double accountBalance, String userName, Integer upiPin) {
        super(accountNumber, accountHolder, accountBalance);
        this.userName = userName;
        this.upiPin = upiPin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(Integer upiPin) {
        this.upiPin = upiPin;
    }

//    pay bill with required parameters of billeruserame, billedAmount, billerType
//    Pay bill only if UPI entered is valid at running time with upiPin of the customer

    public void payBills(String billername,Double billedAmount, String billerType){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the UPI PIN");
        Integer checkpin = scanner.nextInt();
        if (checkpin.equals(getUpiPin())) {
            if (billedAmount <= getAccountBalance()) {
                System.out.println("Bill Amount of "+billedAmount+" paid to "+billername+ "\n Remark "+billerType+"\nTransaction Completed");
            }
            else {
                System.out.println("Insufficient Balance");
            }

        }
        else {
            System.out.println("Incorrect UPI PIN");
        }
    }

}
