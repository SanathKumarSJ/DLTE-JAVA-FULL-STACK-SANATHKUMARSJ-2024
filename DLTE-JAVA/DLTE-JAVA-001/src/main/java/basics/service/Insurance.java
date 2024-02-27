package basics.service;
import java.util.*;
public class Insurance {
    public static void main(String[] args)
    {   //insurance companies with features
        String[] metlifeInc={"sharing risk", "co-operative devices", "good customer service"};
        String[] aflacInc ={"clarity","sharing risk","product quality"};
        String[] humanaInc={"no claim bonus","value of risk","sharing risk"};
        String userPromt="";
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter user need"); // user input
        userPromt=scanner.nextLine();
        System.out.println(userPromt);
        // using conditional statement the insurance company name suggested
        if (metlifeInc.equals(userPromt));
        {
            System.out.println("User can have MetlifeInc Insurance Policy ");
        }
        if (aflacInc.equals(userPromt));
        {
            System.out.println("User can have AflacInc Insurance Policy ");
        }
        if (humanaInc.equals(userPromt));
        {
            System.out.println("User can have HumanaInc Insurance Policy ");
        }
    }
}
