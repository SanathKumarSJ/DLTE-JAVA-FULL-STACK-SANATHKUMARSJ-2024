package basics.service;
import java.util.*;
public class minimumbal {
    public static void main(String[] args) {
        // Declaring the array customerbalance and initializing it in main method
        int[] customerbalance=new int[20];
        Scanner scanner=new Scanner(System.in);
        for(int index=0;index<20;index++)
        {
            //prompt 20 inputs
            System.out.println("Enter "+(index+1)+" User balance");
            customerbalance[index]=scanner.nextInt();

        }
        updatebalance(customerbalance); // calling method updatebalance for updation
        System.out.println("Updated Balances are");
        System.out.println(Arrays.toString(customerbalance));
    }

// method update
    public static void updatebalance(int[] balance) {
        for(int i=0;i<20;i++)
        {
            int currentBal=balance[i];
            if( currentBal>=5000 && currentBal<10000){
                balance[i]-= currentBal*0.03; //3% Charge if the balance is in between 5000 to 10000
            }
            else if(currentBal>=1000 && currentBal<5000)
            {
                balance[i]-=currentBal*0.05; // 5% charges if the balance is in between 1000 to 5000
            }
        }
    }
}
