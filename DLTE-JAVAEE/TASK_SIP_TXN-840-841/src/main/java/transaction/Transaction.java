package transaction;

import java.util.Date;

public class Transaction {
    private Integer transactionId;
    private String transactionFrom;
    private String transactionTo;
    private Double transactionAmount;
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(Integer transactionId, String transactionFrom, String transactionTo, Double transactionAmount, Date transactionDate) {
        this.transactionId = transactionId;
        this.transactionFrom = transactionFrom;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionFrom() {
        return transactionFrom;
    }

    public void setTransactionFrom(String transactionFrom) {
        this.transactionFrom = transactionFrom;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "transactionId=" + transactionId +
//                ", transactionFrom='" + transactionFrom + '\'' +
//                ", transactionTo='" + transactionTo + '\'' +
//                ", transactionAmount=" + transactionAmount +
//                ", transactionDate=" + transactionDate +
//                '}';
//    }
}
