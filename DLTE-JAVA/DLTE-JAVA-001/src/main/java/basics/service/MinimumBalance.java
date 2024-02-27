package basics.service;
import java.util.*;
public class MinimumBalance {
    public static void main(String[] args) {
        // Declaring the array customerbalance and initializing it in main method
        int[] customerBalance=new int[20];
        Scanner scanner=new Scanner(System.in);
        for(int index=0;index<20;index++)
        {
            //prompt 20 inputs
            System.out.println("Enter "+(index+1)+" User balance");
            customerBalance[index]=scanner.nextInt();

        }
        updateBalance(customerBalance); // calling method updatebalance for updation
        System.out.println("Updated Balances are");
        System.out.println(Arrays.toString(customerBalance));
    }

// method update
    public static void updateBalance(int[] balance) {
        for(int index=0;index<20;index++)
        {
            int currentBal=balance[index];
            if( currentBal>=5000 && currentBal<10000){
                balance[index]-= currentBal*0.03; //3% Charge if the balance is in between 5000 to 10000
            }
            else if(currentBal>=1000 && currentBal<5000)
            {
                balance[index]-=currentBal*0.05; // 5% charges if the balance is in between 1000 to 5000
            }
        }
    }
}
