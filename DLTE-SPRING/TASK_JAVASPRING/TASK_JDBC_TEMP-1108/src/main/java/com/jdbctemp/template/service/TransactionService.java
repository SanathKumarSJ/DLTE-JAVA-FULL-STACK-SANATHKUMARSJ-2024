package com.jdbctemp.template.service;

import com.jdbctemp.template.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class TransactionService {

    //JDBC template
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // adding new transaction
    public Transaction apiSave(Transaction transaction){
        int ack = jdbcTemplate.update("insert into Transaction_jdbc values(?,?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionBy(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionRemarks()
                });
        if(ack!=0)
            return transaction;
        else
            throw new TransactionException(" due insertion failed");
    }

    //find transaction by sender
    public Optional<Transaction> apiFindBySender(String tranBy){
        return Optional.of(jdbcTemplate.queryForObject("select * from Transaction_jdbc where transactionBy=?",
                new Object[]{tranBy},
                new BeanPropertyRowMapper<>(Transaction.class)
        ));
    }

    //find transaction by receiver
    public Optional<Transaction> apiFindToReceiver(String tranTo){
        return Optional.of(jdbcTemplate.queryForObject("select * from Transaction_jdbc where transactionTo=?",
                new Object[]{tranTo},
                new BeanPropertyRowMapper<>(Transaction.class)
        ));
    }

    //find transaction by transaction Amount
    public Optional<Transaction> apiFindAmount(Double amount){
        return Optional.of(jdbcTemplate.queryForObject("select * from Transaction_jdbc where transactionBy=?",
                new Object[]{amount},
                new BeanPropertyRowMapper<>(Transaction.class)
        ));
    }
}
