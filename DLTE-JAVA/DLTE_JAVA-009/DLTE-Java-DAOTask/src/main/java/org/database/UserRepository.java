package org.database;

import java.sql.Date;
import java.util.List;

public interface UserRepository {
    void save(User user);
    void callSaveTransaction(Transaction transaction);
    User findById(String username);
    public List<User> findAll();
    public List<Transaction> findAllByUsername(String username);
    public List<Transaction> findAllByDateAndUsername(String username, Date transactionDate);
    public List<Transaction> findAllTransaction();

}
