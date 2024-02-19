package basics.service;
import java.util.Scanner;

public class incometax {
    public static void main(String[] args) {
        double salary = 0.0, ntax, otax;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the salary");
        salary = scanner.nextDouble();
        String choiceUser = "";
        System.out.println("Enter the new regime or old regime");
        choiceUser = scanner.next();
        System.out.println(choiceUser);
        // user need to chose the regime
        switch (choiceUser) {
            case "Newregime": case "or newregime": {
                if (salary <= 250000 && salary >= 0) {
                    System.out.println("According to new tax slab no tax should be paid");
                } else if (salary <= 500000 && salary > 200000) {
                    ntax = 0.0;
                    otax = salary * (5.0 / 100.00);
                    System.out.println("According to new tax slab no tax should be paid that is " + ntax);
                } else if (salary <= 750000 && salary > 500000) {
                    ntax = salary * (10.00 / 100.00);
                    otax = salary * (20.00 / 100.00);
                    System.out.println("According to new tax slab 10% tax should be paid that is " + ntax);
                } else if (salary <= 1000000 && salary > 750000) {
                    ntax = salary * (15.00 / 100.00);
                    otax = salary * (20.00 / 100.00);
                    System.out.println("According to new tax slab 15% tax should be paid that is " + ntax);
                } else if (salary <= 1250000 && salary > 1000000) {
                    ntax = salary * (20.00 / 100.00);
                    otax = salary * (30.00 / 100.00);
                    System.out.println("According to new tax slab 20% tax should be paid that is " + ntax);
                } else {
                    ntax = salary * (30.00 / 100.00);
                    otax = salary * (30.0 / 100.00);
                    System.out.println("According to new tax slab 30% tax should be paid that is " + ntax);
                }
                break;
            }
            case "Oldregime": case "or oldregime": {
                if (salary <= 250000 && salary >= 0) {
                    System.out.println("According to old tax slab no tax should be paid");
                } else if (salary <= 500000 && salary > 200000) {
                    ntax = 0.0;
                    otax = salary * (5.0 / 100.00);
                    System.out.println("According to old tax slab 5% tax should be paid that is " + otax);
                } else if (salary <= 750000 && salary > 500000) {
                    ntax = salary * (10.00 / 100.00);
                    otax = salary * (20.00 / 100.00);
                    System.out.println("According to new tax slab 20% tax should be paid that is " + otax);
                } else if (salary <= 1000000 && salary > 750000) {
                    ntax = salary * (15.00 / 100.00);
                    otax = salary * (20.00 / 100.00);
                    System.out.println("According to old tax slab 20% tax should be paid that is " + otax);
                } else if (salary <= 1250000 && salary > 1000000) {
                    ntax = salary * (20.00 / 100.00);
                    otax = salary * (30.00 / 100.00);
                    System.out.println("According to old tax slab 30% tax should be paid is " + otax);
                } else {
                    ntax = salary * (30.00 / 100.00);
                    otax = salary * (30.0 / 100.00);
                    System.out.println("According to old tax slab 30% tax should be paid is " + otax);
                }

            }
        }
    }
}
