package basics.service;

import java.text.ParseException;
import java.util.Date;

public interface TransactionFunctionality {
    void highestAmount();
    void rangeDate(Transaction[] array, Date start, Date end) throws ParseException;
    void amountacending();
    void beneficiaryDecending(Transaction[] array, Date start, Date end);
    void filterBasedOnRemark();
    void noOftxnOnbeneficiary();
    void leastAmount();

}
