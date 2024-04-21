package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.MyBankOfficials;
import com.paymentdao.payment.exception.PayeeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MyBankOfficialsService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger= LoggerFactory.getLogger(MyBankOfficialsService.class);

    public MyBankOfficials signingUp(MyBankOfficials myBankOfficials){
        int ack = jdbcTemplate.update("insert into MYBANK_APP_CUSTOMER values(CUSTOMERID_SEQ.nextval,?,?,?,?,?,?,1)",new Object[]{
                myBankOfficials.getCustomerName(),myBankOfficials.getCustomerAddress(),myBankOfficials.getCustomerStatus(),myBankOfficials.getCustomerContact(),myBankOfficials.getUserName(),myBankOfficials.getPassword()
        });
        return myBankOfficials;
    }

    public MyBankOfficials findByUsername(String username){
        MyBankOfficials myBankOfficials = jdbcTemplate.queryForObject("select * from MYBANK_APP_CUSTOMER where USERNAME=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankOfficials.class));
        return myBankOfficials;
    }

    public void updateAttempts(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set ATTEMPTS=? where USERNAME=?",
                new Object[]{myBankOfficials.getAttempts(),myBankOfficials.getUsername()});
        logger.info("Attempts are updated");
    }

    public void updateStatus(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{myBankOfficials.getUsername()});
        logger.info("Status has changed");
    }

    public Long getAccountNumberByCustomerId(Long customerId) {
        String sql = "SELECT a.ACCOUNT_NUMBER " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{customerId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new PayeeException("No account found for customer ID: " + customerId);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return null;
        }
    }

    public List<Long> getAccountNumbersByCustomerId(Long customerId) {
        String sql = "SELECT a.ACCOUNT_NUMBER " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
            return jdbcTemplate.queryForList(sql, new Object[]{customerId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new PayeeException("No account found for customer ID: " + customerId);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return Collections.emptyList(); // Or handle the error in an appropriate way
        }
    }








//    public MyBankOfficials signingUp(MyBankOfficials myBankOfficials){
//        int ack = jdbcTemplate.update("insert into MYBANK_APP_CUSTOMR values(?,?,?,?,?,?,?)",new Object[]{
//                myBankOfficials.getUserId(),myBankOfficials.getName(),myBankOfficials.getUserName(),myBankOfficials.getPassword(),
//                myBankOfficials.getEmail(),myBankOfficials.getStatus(),myBankOfficials.getAttempts()
//        });
//        return myBankOfficials;
//    }
//
//    public MyBankOfficials findByUsername(String username){
//        MyBankOfficials myBankOfficials = jdbcTemplate.queryForObject("select * from MYBANK_APP_CUSTOMR where USERNAME=?",
//                new Object[]{username},new BeanPropertyRowMapper<>(MyBankOfficials.class));
//        return myBankOfficials;
//    }
//
//    public void updateAttempts(MyBankOfficials myBankOfficials){
//        jdbcTemplate.update("update MYBANK_APP_CUSTOMR set ATTEMPTS=? where USERNAME=?",
//                new Object[]{myBankOfficials.getAttempts(),myBankOfficials.getUsername()});
//        logger.info("Attempts are updated");
//    }
//
//    public void updateStatus(MyBankOfficials myBankOfficials){
//        jdbcTemplate.update("update MYBANK_APP_CUSTOMR set STATUS=0 where username=?",
//                new Object[]{myBankOfficials.getUsername()});
//        logger.info("Status has changed");
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankOfficials officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}