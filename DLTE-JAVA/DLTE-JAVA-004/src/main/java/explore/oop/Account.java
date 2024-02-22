package explore.oop;

public class Account {
    Long AccountNumber;
    String AccountHolder;
    Double AccountBalance;

    public Account(Long accountNumber, String accountHolder, Double accountBalance) {
        AccountNumber = accountNumber;
        AccountHolder = accountHolder;
        AccountBalance = accountBalance;
    }

    public Long getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return AccountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        AccountHolder = accountHolder;
    }

    public Double getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        AccountBalance = accountBalance;
    }
}
