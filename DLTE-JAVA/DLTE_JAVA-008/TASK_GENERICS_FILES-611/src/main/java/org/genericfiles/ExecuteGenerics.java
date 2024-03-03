package org.genericfiles;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExecuteGenerics {
    static ArrayList<CreditCard> creditCards = new ArrayList<>();
    static File file = new File("C:\\DLTE-JAVA-FULL-STACK-SANATHKUMARSJ-2024\\DLTE-JAVA\\DLTE_JAVA-008\\TASK_GENERICS_FILES-611\\src\\main\\credit.txt");

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ExecuteGenerics executeGenerics = new ExecuteGenerics();
        if (file.isFile())
            System.out.println("File created");
        else {
            System.out.println("Not created");
        }
        executeGenerics.create(new CreditCard(7846535465L,"Sanath",100000,new Date(2024,12,11)));
        executeGenerics.create( new CreditCard(1242655465L,"Razzak",80000,new Date(2025,1,01)));
        executeGenerics.create( new CreditCard(2525365465L,"Akash",90000,new Date(2024,12,21)));
        executeGenerics.create( new CreditCard(4484465465L,"Mahesh",110000,new Date(2024,8,15)));
    }


    public void create(CreditCard credit) throws IOException, ClassNotFoundException {
        creditCards.add(credit);
        appendToFile();
        System.out.println("Credit Card Added Successfully");
    }
    //writing
    private void appendToFile() throws IOException, ClassNotFoundException {
        FileOutputStream outputFile=new FileOutputStream(file);
        ObjectOutputStream outputCredit = new ObjectOutputStream(new FileOutputStream(file,true));
        outputCredit.writeObject(creditCards);
        outputCredit.close();
        outputFile.close();

    }
    }
