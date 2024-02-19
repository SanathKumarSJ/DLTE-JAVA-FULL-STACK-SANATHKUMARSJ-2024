package basic.service;
import java.util.Scanner;

public class mobilebanking {

    public static void main(String[] args) {
        String userPIN="",destName="",destAcc="",destPh="";
        Long userotp=0L,amount=0L,transferotp=0L;
        Scanner scanner= new Scanner(System.in);
        System.out.println("--------------Welocome to Mobile Banking-----------------");
        System.out.println("Enter PIN");
        userPIN=scanner.nextLine();
        System.out.println("--------------Enter Recipients details-----------------");
        System.out.println("Fill Recipients name/ Recipients account number/  Recipients Phone number");
        destName=scanner.nextLine();
        System.out.println("Enter Amount");
        amount=scanner.nextLong();
        System.out.println("Enter UPI PIN");
        transferotp=scanner.nextLong();
        System.out.println("The amount of Rs="+amount+" is transferred to "+destName+" successfully");
    }
}