package org.exceptionlog;

public class AccountAnalysis {

    public static void main(String[] args) {
        System.out.println("---UPI BILL PAYMENT-----");
        //Online UPI bill payment
        Gpay[] gpaycustomer = new Gpay[5];

        gpaycustomer[0] = new Gpay(784510202L, "SanathKumar", 40000.00, "sanathkumarsj", 8858);
        // Biller details

        try {
            gpaycustomer[0].payBills("Rohith", 20000.00, "Friend");
        } catch (MyBankException e) {
            throw new MyBankException();
        }
    }
}