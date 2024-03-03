package org.concurrencylock;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Resource implements Runnable {
    Lock lock = new ReentrantLock();
    static ArrayList<Transaction> transaction = new ArrayList<>();
    static Comparator<Transaction> comparator = null;

    public void rangeDate(List<Transaction> transactionsList, Date start, Date end) {
        System.out.println("Transactions made in between " + start.getDate() + " and " + end.getDate());
        List<Transaction> filter = transactionsList.stream().filter(transaction -> transaction.getDateOfTransaction().compareTo(start) >= 0 && transaction.getDateOfTransaction().compareTo(end) <= 0).collect(Collectors.toList());
        filter.forEach(System.out::println);
    }

    public void leastAmount(List<Transaction> array) {
        // using stream().min() finding least amount
        Transaction leastAmount = array.stream().min(Comparator.comparing(Transaction::getTransactionAmount)).orElse(null);
        System.out.println("The least amount transferred is " + leastAmount);
    }

    public void highestAmount(List<Transaction> array) {
        // using selection sorting finding least amount
        Transaction maxAmount = array.stream().max(Comparator.comparing(Transaction::getTransactionAmount)).orElse(null);
        System.out.println("The highest amount transferred is " + maxAmount);
    }

    public void noOftxnOnbeneficiary(List<Transaction> transactionsList) {
        Scanner scanner = new Scanner(System.in);
        int countNoOfTransaction = 0;
        System.out.println("Enter the beneficiary");
        String beneficiary = scanner.nextLine();
        for (Transaction each : transactionsList) {
            // comparing the beneficiary with the data
            if (each.getRemarks().equals(beneficiary)) {
                countNoOfTransaction += 1;
            }
        }
        System.out.println("Based on " + beneficiary + " " + countNoOfTransaction + " transactions performed");
    }

    public void filterBasedOnRemark(List<Transaction> transactionsList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the beneficiary");
        String beneficiary = scanner.nextLine();
        List<Transaction> filter = transactionsList.stream().filter(transaction -> transaction.getRemarks().compareTo(beneficiary) >= 0).collect(Collectors.toList());
        filter.forEach(System.out::println);

    }

    @Override
    public void run() {
        lock.lock();
        System.out.println("Welcome to my Bank");
        System.out.println("Choose \n1.To find highest Amount \n 2.To get transaction on given Date range \n3. To get amount in ascending order \n4. To get beneficiary in decending order \n5. To filter based on Remark \n6. To get no.of transaction made on selected beneficiary \n7. To get leastAmount");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                highestAmount(transaction);
                break;
            case 2:
                rangeDate(transaction, new Date(2024, 8, 5), new Date(2024, 12, 29));
                break;
            case 3:
                comparator = Comparator.comparing(Transaction::getTransactionAmount);
                break;
            case 4:
                comparator = Comparator.comparing(Transaction::getRemarks);
                break;
            case 5:
                filterBasedOnRemark(transaction);
                break;
            case 6:
                noOftxnOnbeneficiary(transaction);
                break;
            case 7:
                leastAmount(transaction);
                break;
        }
        lock.unlock();
        transaction.sort(comparator);
        System.out.println("---Sorted--");
        transaction.forEach(System.out::println);
    }
    }
