package basic.service;
import java.util.Scanner;
// command line interaction: Personal loan

/* Personal details : name,aadhaar,pan,address, mobile,email
   Income: salaried, self-employed:ITR

 */

public class personal_loan {
    public static void main(String[] args) {
        String borrowerName="", borrowerPan="" ,borrowerAddress="", borrowerEmail="",borrowerIncTyp="";
        Long salary=0L,mobile=0L,adhar=0L;
        Scanner scanner= new Scanner(System.in);
        System.out.println("--------------Welocome to My Bank-----------------");
        System.out.println("Fill your name");
        borrowerName=scanner.nextLine();
        System.out.println("Fill your adhar number");
        adhar=scanner.nextLong();
        System.out.println("Enter the pan");
        borrowerPan=scanner.next();
        System.out.println("Enter your salary");
        salary=scanner.nextLong();
        System.out.println("Let us know income type (Salaried/self-employed)");
        borrowerIncTyp=scanner.next();
        System.out.println("Fill your email");
        borrowerEmail=scanner.next();
        System.out.println("Enter the mobile number");
        mobile=scanner.nextLong();
        System.out.println("Dear "+borrowerName+" Thanks for showing interest in taking the personal loan in my bank your application has submitted and further details will be mailed to you "+borrowerEmail+" or SMS to "+mobile);
    }


}