package basics.service;

import java.util.Date;

import static basics.service.TransactionAnalysis.myBank;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        TransactionAnalysis transactionAnalysis= new TransactionAnalysis();
        Thread thread1 = new Thread(transactionAnalysis::highestAmount,"User1");
        Thread thread2 = new Thread(transactionAnalysis::leastAmount,"User2");
        Thread thread3 = new Thread(transactionAnalysis::beneficiaryDecending,"User3");
        Thread thread4 = new Thread(transactionAnalysis,"User4");
        Thread thread5 = new Thread(transactionAnalysis,"User5");
        Thread thread6 = new Thread(transactionAnalysis,"User6");
        Thread thread7 = new Thread(transactionAnalysis,"User7");
        Thread thread8 = new Thread(transactionAnalysis,"User8");
        Thread thread9 = new Thread(transactionAnalysis,"User9");
        Thread thread10 = new Thread(transactionAnalysis,"User10");

        //System.out.println("Executing Thread");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
}
}
