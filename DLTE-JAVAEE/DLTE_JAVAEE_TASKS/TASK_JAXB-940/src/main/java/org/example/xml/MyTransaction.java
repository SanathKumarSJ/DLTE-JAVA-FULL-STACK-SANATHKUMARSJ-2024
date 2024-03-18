package org.example.xml;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class MyTransaction {
        private List<Transaction> myBanks;

    public MyTransaction() {
    }
    @XmlElement(name = "transaction")
    public List<Transaction> getMyBanks() {
        return myBanks;
    }

    public void setMyBanks(List<Transaction> myBanks) {
        this.myBanks = myBanks;
    }

    public MyTransaction(List<Transaction> myBanks) {
        this.myBanks = myBanks;
    }
}
