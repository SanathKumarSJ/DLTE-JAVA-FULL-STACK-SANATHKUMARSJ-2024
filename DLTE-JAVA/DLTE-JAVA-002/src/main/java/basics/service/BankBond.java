package basics.service;
import java.util.*;
public class BankBond {
    private Date maturityOfTheBond;
    private Double InterestRate;
    private String taxStatus;
    private String bondHolder;
    private Integer periodOfBond;

    public Date getMaturityOfTheBond() {
        return maturityOfTheBond;
    }

    public void setMaturityOfTheBond(Date maturityOfTheBond) {
        this.maturityOfTheBond = maturityOfTheBond;
    }

    public Double getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.InterestRate = InterestRate;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public String getBondHolder() {
        return bondHolder;
    }

    public void setBondHolder(String bondHolder) {
        this.bondHolder = bondHolder;
    }

    public Integer getPeriodOfBond() {
        return periodOfBond;
    }

    public void setPeriodOfBond(Integer periodOfBond) {
        this.periodOfBond = periodOfBond;
    }

    public BankBond(Date maturityOfTheBond, Double InterestRate, String taxStatus, String bondHolder, Integer periodOfBond) {
        this.maturityOfTheBond = maturityOfTheBond;
        this.InterestRate = InterestRate;
        this.taxStatus = taxStatus;
        this.bondHolder = bondHolder;
        this.periodOfBond = periodOfBond;


    }

    @Override
    public String toString() {
        return "BankBond{" +
                "maturityOfTheBond=" + maturityOfTheBond +
                ", interestRate=" + InterestRate +
                ", taxStatus='" + taxStatus + '\'' +
                ", bondHolder='" + bondHolder + '\'' +
                ", periodOfBond=" + periodOfBond +
                '}';
    }
}
