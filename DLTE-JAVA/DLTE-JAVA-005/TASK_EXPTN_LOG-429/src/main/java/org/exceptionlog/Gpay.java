package org.exceptionlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Gpay extends Account {
    String userName;
    Integer upiPin;

    public Gpay(Long accountNumber, String accountHolder, Double accountBalance, String userName, Integer upiPin) {
        super(accountNumber, accountHolder, accountBalance);
        this.userName = userName;
        this.upiPin = upiPin;
    }

    public Gpay() {
        super();
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

    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private static Logger logger= LoggerFactory.getLogger(Gpay.class);

    public void payBills(String billername,Double billedAmount, String billerType){
        Scanner scanner = new Scanner(System.in);
        int attempt=0;
        System.out.println("Enter the UPI PIN");
        Integer checkpin = scanner.nextInt();
        while (!getUpiPin().equals(checkpin)){
            attempt++;
            if(attempt>=5){
                logger.warn(resourceBundle.getString("pin.block"));
                throw new MyBankException(resourceBundle.getString("bank.visit"));
            }
            // attempting
            logger.info(resourceBundle.getString("pin.invalid"));
            System.out.println("You have only "+(5-attempt)+" attempts");
            System.out.println("Enter the UPI PIN");
            checkpin = scanner.nextInt();
        }
        logger.info(resourceBundle.getString("valid.upi"));


        if (billedAmount <= getAccountBalance()) {
            System.out.println("Bill Amount of "+billedAmount+" paid to "+billername+ "\n Remark "+billerType);
            logger.info(resourceBundle.getString("transaction.finish"));
        }
        else {
            logger.info(resourceBundle.getString("balance.error"));
        }
    }
}
