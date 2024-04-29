package com.example.demo.service;

import com.example.demo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    //JDBC template
    @Autowired
    private JdbcTemplate jdbcTemplate;
//
    //finding all transaction
    public List<Transaction> apiFindAll() {
        List<Transaction> myList = null;
        myList = jdbcTemplate.query("select * from Transaction_jdbc", new BeanPropertyRowMapper<>(Transaction.class));//,new BeanPropertyRowMapper<>(Transaction.class);
        return myList;
    }
    //BeanPropertyRowMapper helps in mapping rows of a ResultSet to objects.
    // In this case, it's being used to map rows from the Transaction_jdbc table to Transaction objects.
    // It automatically matches columns in the ResultSet with properties of the Transaction class based on naming conventions.
    // So, each column in the result set is mapped to a property of the Transaction class with a matching name.



    // adding new transaction
    public Transaction apiSave(Transaction transaction) {
        int ack = jdbcTemplate.update("insert into Transaction_jdbc values(?,?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionBy(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionRemarks()
                });
        if (ack != 0)
            return transaction;
        else
            throw new TransactionException(" due insertion failed");
    }

    //find transaction by sender
    public Optional<Transaction> apiFindBySender(String tranBy) {
        return Optional.of(jdbcTemplate.queryForObject("select * from Transaction_jdbc where transactionBy=?",
                new Object[]{tranBy},
                new BeanPropertyRowMapper<>(Transaction.class)
        ));
    }

    //find transaction by receiver
    public Optional<Transaction> apiFindToReceiver(String tranTo) {
        return Optional.of(jdbcTemplate.queryForObject("select * from Transaction_jdbc where transactionTo=?",
                new Object[]{tranTo},
                new BeanPropertyRowMapper<>(Transaction.class)
        ));
    }

    //find transaction by transaction Amount
    public Optional<Transaction> apiFindAmount(Double amount) {
        return Optional.of(jdbcTemplate.queryForObject("select * from Transaction_jdbc where TRANSACTIONAMOUNT=?",
                new Object[]{amount},
                new BeanPropertyRowMapper<>(Transaction.class)
        ));
    }
    public Transaction updateOnRemark(Transaction transaction){
        int ack = jdbcTemplate.update("update Transaction_jdbc set TRANSACTIONREMARKS=? where TRANSACTIONID=?",
                new Object[]{transaction.getTransactionRemarks(),transaction.getTransactionId()});
        if(ack!=0)
            return transaction;
        else
            return null;
    }

    public String removeOnDate(Date date1, Date date2){
        int ack = jdbcTemplate.update("delete from Transaction_jdbc WHERE TRANSACTIONDATE BETWEEN ? OR ?",new Object[]{date1,date2});
        if(ack!=0)
            return "removed";
        else
            return "No transaction found";
    }


    public class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transactions=new Transaction();
            transactions.setTransactionId(rs.getLong(1));
            transactions.setTransactionDate(rs.getDate(2));
            transactions.setTransactionBy(rs.getString(3));
            transactions.setTransactionTo(rs.getString(4));
            transactions.setTransactionAmount(rs.getDouble(5));
            transactions.setTransactionRemarks(rs.getString(6));

            return transactions;
        }
    }
}
