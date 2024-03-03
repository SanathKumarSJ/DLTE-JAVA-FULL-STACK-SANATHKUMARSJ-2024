package org.genericstransaction;
import java.util.*;
import java.util.stream.Collectors;

public class transactionAnalysis {
    public static void main(String[] args) {
        List<Transaction> transactions=new ArrayList<>();
        transactions.add(new Transaction(12500.89, "Maneesh", new Date(2024, 3, 15), "emergency"));
        transactions.add(new Transaction(78625.48, "Rahul", new Date(2024, 6, 3), "family"));
        transactions.add(new Transaction(857895.00, "Ashwin", new Date(2024, 8, 28), "education"));
        transactions.add(new Transaction(1784521.00, "Rohith", new Date(2024, 11, 8), "bill"));
        transactions.add(new Transaction(12575.89, "Vibha", new Date(2024, 8, 21), "emergency"));
        Scanner scanner = new Scanner(System.in);
        // creating object

        transactionAnalysis analyse = new transactionAnalysis();
        //finding transaction in the date range
        analyse.rangeDate(transactions, new Date(2024, 8, 5), new Date(2024, 12, 29));
        // to find least amount
        analyse.leastAmount(transactions);

        // to find highest amount transferred
        analyse.highestAmount(transactions);

        System.out.println("Please enter the property Transaction-> date,amount,to,remark");
        String property=scanner.next();

        System.out.println("Please enter the order-> asc(ascending),dsc(descending)");
        String order = scanner.next();
        Comparator<Transaction> comparator=null;
        switch (property){
            case "date": case "dateoftransaction":
                comparator = Comparator.comparing(Transaction::getDateOfTransaction);
                break;
            case "amount" : case "amountoftransaction":
                comparator = Comparator.comparing(Transaction::getTransactionAmount);
                break;
            case "to": case "transactionto":
                comparator = Comparator.comparing(Transaction::getTransactionTo);
                break;
            case "remark" : case "transactionremark":
                comparator = Comparator.comparing(Transaction::getRemarks);
                break;
            default:
                System.out.println("Invalid");
                System.exit(0);
        }
        if(order.equals("dsc")){
            comparator=comparator.reversed();//reversing the order
        }
        transactions.sort(comparator);
        System.out.println("---Sorted--");
        transactions.forEach(System.out::println);
    }

    public void rangeDate(List<Transaction> transactionsList, Date start, Date end) {
        System.out.println("Transactions made in between " + start.getDate() + " and " + end.getDate());
        List<Transaction> filter=transactionsList.stream().filter(transaction -> transaction.getDateOfTransaction().compareTo(start)>=0&&transaction.getDateOfTransaction().compareTo(end)<=0).collect(Collectors.toList());
        filter.forEach(System.out::println);
    }

    public void leastAmount(List<Transaction> array) {
        // using stream().min() finding least amount
        Transaction leastAmount=array.stream().min(Comparator.comparing(Transaction::getTransactionAmount)).orElse(null);
        System.out.println("The least amount transferred is " +leastAmount);
    }

    public void highestAmount(List<Transaction> array) {
        // using selection sorting finding least amount
        Transaction maxAmount=array.stream().max(Comparator.comparing(Transaction::getTransactionAmount)).orElse(null);
        System.out.println("The least amount transferred is " +maxAmount);
    }


}