package basics.service;
import java.util.*;
public class BondAnalysis {
    public static void main(String[] args) {
        BankBond[] myBank = {
                new BankBond(new Date(2024, 9, 30), 15.0, "no", "Sanath", 5),
                new BankBond(new Date(2025, 5, 20), 14.0, "no", "Akash", 4),
                new BankBond(new Date(2026, 6, 17), 12.0, "yes", "Jonh", 6),
                new BankBond(new Date(2027, 7, 14), 22.0, "yes", "Mahesh", 7),
                new BankBond(new Date(2028, 8, 11), 20.0, "yes", "Dhanush", 9),
        };
        BondAnalysis bondAnalysis = new BondAnalysis();
        // display before sorting
        System.out.println("Before Sorting");
        bondAnalysis.display(myBank);

        // Sort
        bondAnalysis.sortBond(myBank);

        // display After sorting
        System.out.println("After Sorting");
        bondAnalysis.display(myBank);
    }

    public void display(BankBond[] array){
        for(BankBond each:array){
            System.out.println(each);
        }
    }

    public void sortBond(BankBond[] array){
        // sorting the Bond based on interest
        BankBond backup = null;
        for (int select = 0; select < array.length; select++) {
            for (int next = 0; next < array.length - select - 1; next++) {
                // swapping the name
                if (array[next + 1].getInterestRate().compareTo(array[next].getInterestRate()) > 0) {
                    backup = array[next];
                    array[next] = array[next + 1];
                    array[next + 1] = backup;
                }
            }
        }
    }
}

