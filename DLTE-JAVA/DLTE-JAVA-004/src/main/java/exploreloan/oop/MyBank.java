package exploreloan.oop;

import java.util.Date;

public interface MyBank {
    // abstract methods
    LoanInfo[] loanInfo=new LoanInfo[10];
    default void initialize() {
        loanInfo[0] = new LoanInfo(465354454L, 78000.0, new Date(2024, 12, 2), "open", "Sanath", 85161563456L);
        loanInfo[1] = new LoanInfo(442577454L, 41400.0, new Date(2024, 11, 12), "close", "Rohith", 454523456L);
        loanInfo[2] = new LoanInfo(868554454L, 17700.0, new Date(2024, 8, 22), "open", "Karan", 98753456L);
        loanInfo[3] = new LoanInfo(6347584454L, 56000.0, new Date(2024, 10, 6), "close", "Akash", 897783456L);
    }
    void checkAvailableLoan();
    void getCheckClosedLoan();
    void addLoan();

}
