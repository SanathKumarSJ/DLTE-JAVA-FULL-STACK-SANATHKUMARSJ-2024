package org.example.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Unmarshalling {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        JAXBContext context=JAXBContext.newInstance(MyTransaction.class);
        Unmarshaller unmarshaller= context.createUnmarshaller();
        MyTransaction myTransaction =(MyTransaction) unmarshaller.unmarshal(new FileInputStream("Newtransaction.xml"));
        List<Transaction> transactionList = myTransaction.getMyBanks();
//        System.out.println(transactionList.toString());
        Scanner scanner=new Scanner(System.in);
        // enter name
        System.out.println("Enter the name");
        String userName=scanner.next();
        // filtering transaction based on name
        for (Transaction each:transactionList){
//            System.out.println(each.getTransactionTo());
            if(each.getTransactionTo().equalsIgnoreCase(userName)) {
                System.out.println(each);
            }
        }
    }
}