package org.genericfiles;

import java.io.Serializable;
import java.util.Date;

public class CreditCard implements Serializable {
    private Long creditCardNumber;
    private String creditCardHolder;
    private Integer creditCardLimit;
    private Date creditCardExpiry;

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }

    public Integer getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Integer creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public Date getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(Date creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public CreditCard(Long creditCardNumber, String creditCardHolder, Integer creditCardLimit, Date creditCardExpiry) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCardLimit = creditCardLimit;
        this.creditCardExpiry = creditCardExpiry;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", creditCardHolder='" + creditCardHolder + '\'' +
                ", creditCardLimit=" + creditCardLimit +
                ", creditCardExpiry=" + creditCardExpiry +
                '}'+'\n';
    }
}
