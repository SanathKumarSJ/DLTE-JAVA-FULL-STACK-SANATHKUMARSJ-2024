package web;

import org.database.Transaction;

import java.util.List;

public class GroupTransaction {

    private List<Transaction> transactions;

    @Override
    public String toString() {
        return "GroupTransaction{" +
                "transactions=" + transactions +
                '}';
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
