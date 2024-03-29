package com.transaction.jdbc.demo.DAO;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //new transaction
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

    //findBySender
    public List<Transaction> apiFindBySender(String tranBy) {
        return jdbcTemplate.query("select * from Transaction_jdbc where transactionBy=?",
                new Object[]{tranBy},
                new TransactionMapper());
    }

    //findByReceiver
    public List<Transaction> apiFindToReceiver(String tranBy) {
        return jdbcTemplate.query("select * from Transaction_jdbc where transactionTo=?",
                new Object[]{tranBy},
                new TransactionMapper());
    }

    //findByAmount
    public List<Transaction> apiFindAmount(Double amount) {
        return jdbcTemplate.query("select * from Transaction_jdbc where transactionAmount=?",
                new Object[]{amount},
                new TransactionMapper());
    }

    //updateRemarks
    public Transaction updateOnRemark(Transaction transaction){
        int ack = jdbcTemplate.update("update Transaction_jdbc set TRANSACTIONREMARKS=? where TRANSACTIONID=?",
                new Object[]{transaction.getTransactionRemarks(),transaction.getTransactionId()});
        if(ack!=0)
            return transaction;
        else
            return null;
    }

    public String removeOnDate(Date date1, Date date2){
        int ack = jdbcTemplate.update("delete from Transaction_jdbc where TRANSACTIONDATE BETWEEN ? OR ?",new Object[]{date1,date2});
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
