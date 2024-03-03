package explore.oop;

public class AccountAnalysis{
    public static void main(String[] args) {

        DebitCard[] debitCardCustomer = new DebitCard[5];
        debitCardCustomer[0]= new DebitCard(78452262352L,"Sanath", 78000.00, 78945656456416L, 4564);
        // call Amount withdraw
        debitCardCustomer[0].amountWithdraw();
        System.out.println("---UPI BILL PAYMENT-----");
        //Online UPI bill payment
        Gpay[] gpaycustomer = new Gpay[5];
        gpaycustomer[0]= new Gpay(784510202L,"SanathKumar",40000.00,"sanathkumarsj",8858);
        // Biller details
        gpaycustomer[0].payBills("Rohith",20000.00,"Friend");
    }
}
