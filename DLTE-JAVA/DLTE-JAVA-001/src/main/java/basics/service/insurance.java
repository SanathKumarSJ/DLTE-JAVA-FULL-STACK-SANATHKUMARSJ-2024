package basics.service;
import java.util.*;
public class insurance {
    public static void main(String[] args)
    {   //insurance companies with features
        String[] MetlifeInc={"sharing risk", "co-operative devices", "good customer service"};
        String[] AflacInc ={"clarity","sharing risk","product quality"};
        String[] HumanaInc={"no claim bonus","value of risk","sharing risk"};
        String userPromt="";
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter user need"); // user input
        userPromt=scanner.nextLine();
        System.out.println(userPromt);
        // using conditional statement the insurance company name suggested
        if (MetlifeInc.equals(userPromt));
        {
            System.out.println("User can have MetlifeInc Insurance Policy ");
        }
        if (AflacInc.equals(userPromt));
        {
            System.out.println("User can have AflacInc Insurance Policy ");
        }
        if (HumanaInc.equals(userPromt));
        {
            System.out.println("User can have HumanaInc Insurance Policy ");
        }
    }
}
