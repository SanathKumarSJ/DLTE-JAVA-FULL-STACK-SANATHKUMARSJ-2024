package basic.service;
import java.util.Scanner;
public class Internetbanking {
    public static void main(String[] args) {
        String userName="", userPass="" ,userAuthCode="", userEmail="",destName="",destAcc="",destPh="";
        Long userotp=0L,amount=0L,transferotp=0L;
        Scanner scanner= new Scanner(System.in);
        System.out.println("--------------Welocome to Internet Banking-----------------");
        System.out.println("Fill your name");
        userName=scanner.nextLine();
        System.out.println("Fill your Password");
        userPass=scanner.nextLine();
        System.out.println("Enter the Email/Registered Phone number");
        userEmail=scanner.next();
        System.out.println("Enter otp");
        userotp=scanner.nextLong();
        System.out.println("Enter two step verification authentication code");
        userAuthCode=scanner.next();
        System.out.println("Dear "+userName+" Thanks for showing interest");
        System.out.println("--------------Enter Recipients details-----------------");
        System.out.println("Fill Recipients name");
        destName=scanner.nextLine();
        System.out.println("Fill Recipients account number");
        destAcc=scanner.nextLine();
        System.out.println("Enter Recipients Phone number");
        destPh=scanner.next();
        System.out.println("Enter Amount");
        amount=scanner.nextLong();
        System.out.println("Enter otp");
        transferotp=scanner.nextLong();
        System.out.println("Dear "+userName+" the amount of Rs="+amount+" is transferred to "+destName+" successfully");
    }
}
