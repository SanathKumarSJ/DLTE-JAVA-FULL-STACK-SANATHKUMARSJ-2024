package basics.service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarLoanRegx {
    public static void main(String[] args) {
            String borrowerName="", borrowerPan="" ,borrowerAddress="", borrowerEmail="",borrowerIncTyp="";
            Long mobile=0L,adhar=0L;
            Scanner scanner= new Scanner(System.in);
            System.out.println("--------------Welocome to My Bank-----------------");
            System.out.println("Fill your name");
            borrowerName=scanner.nextLine();
            Pattern pattern=Pattern.compile("^[a-zA-Z]+\\s[A-Za-z]+$");// name characters followed by whitespace 's' followed by characters
            Matcher matcher = pattern.matcher(borrowerName);
            if(matcher.matches())
            {
                System.out.println("Username "+borrowerName+" is Valid");
            }
            else
            {
                System.out.println("Username "+borrowerName+" is In-valid");
            }
            System.out.println("Fill your adhar number");
            adhar=scanner.nextLong();
            // check for 12 digit adhar number
            Pattern pattern1=pattern.compile("^\\d{12}");
            String value=String.valueOf(adhar); // string conversion
            Matcher matcher1=pattern1.matcher(value);
            if(matcher1.matches())
            {
                System.out.println("Adhar "+adhar+" is Valid");
            }
            else
            {
                System.out.println("Adhar "+adhar+" is In-valid");
            }
            // check PAN for first five alphabet then 4 numeric then a alphabet
            System.out.println("Enter the pan");
            borrowerPan=scanner.next();
            Pattern pattern3=Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
            Matcher matcher3 = pattern.matcher(borrowerPan);
            if(matcher3.matches())
            {
                System.out.println("Username "+borrowerPan+" is Valid");
            }
            else
            {
                System.out.println("Username "+borrowerPan+" is In-valid");
            }
            System.out.println("Fill your email");
            borrowerEmail=scanner.next();
            String email_expression="^[A-Za-z0-9+-_]{3,}@[A-Za-z]{4,}\\.[a-z]{2,}";
            Pattern pattern2 = Pattern.compile(email_expression);
            Matcher matcher2=pattern2.matcher(borrowerEmail);
            if(matcher2.matches())
            {
                    System.out.println("The Email "+borrowerEmail+" is valid");
            }
            else{
                    System.out.println("The Email "+borrowerEmail+" is In-valid");
            }
            System.out.println("Enter the mobile number");
            mobile=scanner.nextLong();
            // Check for 10 digit phone number and covert the long to string
            Pattern pattern5=pattern.compile("^\\d{10}");
            String value1=String.valueOf(mobile); // string conversion
            Matcher matcher5=pattern5.matcher(value1);
            if(matcher5.matches())
            {
                System.out.println("Mobile "+mobile+" is Valid");
            }
            else
            {
                System.out.println("Adhar "+mobile+" is In-valid");
            }
            System.out.println("Dear "+borrowerName+" Thanks for showing interest in taking the car loan in my bank your application has submitted and further details will be mailed to you "+borrowerEmail+" or SMS to "+mobile);
        }


    }
