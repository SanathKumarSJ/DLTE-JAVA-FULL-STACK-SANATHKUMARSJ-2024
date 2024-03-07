package basics.service;
import javax.swing.*;
import java.util.*;
public class Insurance {
    //insurance companies with features
    String[] metlifeInc = {"sharing risk", "co-operative devices", "good customer service"};
    String[] aflacInc = {"clarity", "sharing risk", "product quality"};
    String[] humanaInc = {"no claim bonus", "value of risk"};

    public static void main(String[] args) {
        Insurance insurance = new Insurance();
        // hardcode
        insurance.findBestPolicy("sharing risk");
    }


    public void findBestPolicy(String userPromt){
        // using conditional statement the insurance company name suggested
        int flag=0;
        System.out.println("Your input is "+userPromt);
        if (Arrays.asList(metlifeInc).contains(userPromt))
        {
            System.out.println("User can have MetlifeInc Insurance Policy ");
            flag=1;
        }
        if (Arrays.asList(aflacInc).contains(userPromt))
        {
            System.out.println("User can have AflacInc Insurance Policy ");
            flag=1;
        }
        if (Arrays.asList(humanaInc).contains(userPromt)) {
            System.out.println("User can have HumanaInc Insurance Policy ");
            flag=1;
        }
        if(flag==0)
        {
            System.out.println("no policy available");
        }

    }
}
