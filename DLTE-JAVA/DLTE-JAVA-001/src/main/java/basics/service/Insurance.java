package basics.service;
import javax.swing.*;
import java.util.*;
public class Insurance {
    //insurance companies with features
    private static final String[][] providerFeature=
    {{"metlifeInc","sharing risk", "co-operative devices", "good customer service"},
    {"aflacInc","clarity", "sharing risk", "product quality","low interest"},
            {"humanaInc","no claim bonus", "value of risk","instant withdrawal","fast service"}};

    public static void main(String[] args) {
        String inputFeature= providerFeature[1][2];
        String insuranceProvider=findBestPolicy(inputFeature);
        System.out.println("For "+inputFeature+" suggested insurance policy is: "+insuranceProvider);
    }


    public static String findBestPolicy(String userPromt){
        // using conditional statement the insurance company name suggested
        for (String[] each:providerFeature){
            String provider=each[0];
            for(int index=1;index<each.length; index++){
                if( each[index].equals(userPromt)){
                    return provider;
                }
            }
        }
        return "No provider Found for feature "+userPromt;
    }
}
