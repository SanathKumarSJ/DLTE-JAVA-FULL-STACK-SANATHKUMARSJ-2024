package org.genericfiles;

import org.genericfiles.CreditCard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class MyBankDatabase {
    static ArrayList<CreditCard> creditCard = new ArrayList<>();
    static File file = new File("C:\\DLTE-JAVA-FULL-STACK-SANATHKUMARSJ-2024\\DLTE-JAVA\\DLTE_JAVA-008\\TASK_GENERICS_FILES-611\\src\\main\\credit.txt");

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase myBankDatabase = new MyBankDatabase();
        myBankDatabase.create(new CreditCard(7846535465L, "Sanath", 100000, new Date(2024, 12, 11)));
        myBankDatabase.create(new CreditCard(1242655465L, "Razzak", 80000, new Date(2025, 1, 01)));
        myBankDatabase.create(new CreditCard(2525365465L, "Akash", 90000, new Date(2024, 12, 21)));
        myBankDatabase.create(new CreditCard(4484465465L, "Mahesh", 110000, new Date(2024, 8, 15)));

    }

    public void create(CreditCard credit) throws IOException, ClassNotFoundException {
        creditCard.add(credit); //array list append
        addToFile();  //adding it into file
        System.out.println("Credit Card Added Successfully");
    }

    private void addToFile() throws IOException, ClassNotFoundException {
        // using append mode adding the details whenever it get created
        FileOutputStream outputFile = new FileOutputStream(file);
        ObjectOutputStream outputLoan = new ObjectOutputStream(new FileOutputStream(file, true));
        outputLoan.writeObject(creditCard);
        outputLoan.close();
        outputFile.close();
    }
}