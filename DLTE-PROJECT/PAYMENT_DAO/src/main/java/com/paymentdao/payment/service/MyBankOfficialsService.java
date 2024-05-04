package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Customer;
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

    public Customer signingUp(Customer myBankOfficials){
        int ack = jdbcTemplate.update("insert into MYBANK_APP_CUSTOMER values(CUSTOMERID_SEQ.nextval,?,?,?,?,?,?,1)",new Object[]{
                myBankOfficials.getCustomerName(),myBankOfficials.getCustomerAddress(),myBankOfficials.getCustomerStatus(),myBankOfficials.getCustomerContact(),myBankOfficials.getUserName(),myBankOfficials.getPassword()
        });
        return myBankOfficials;
    }



    public List<Customer> findAllUsername(){
        List<Customer> customerList;
        customerList=jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER",new BeanPropertyRowMapper<>(Customer.class));
        return customerList;
    }


    //find customer by username
    public Customer findByUsername(String userName){
        List<Customer> customers=findAllUsername();
        Customer customer = customers.stream()
                .filter(each -> each.getUsername().equals(userName)).findFirst().orElse(null);

        if(customer==null){
                throw new UsernameNotFoundException(userName);
        }
        return customer;
    }

    //update the login attempts
    public void updateAttempts(Customer customer){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set ATTEMPTS=? where USERNAME=?",
                new Object[]{customer.getAttempts(),customer.getUsername()});
        logger.info("Attempts are updated");
    }

    //update the customer login status
    public void updateStatus(Customer customer){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{customer.getUsername()});
        logger.info("Status has changed");
    }


    public List<Long> getAccountList(Long customerId) {
        String sql = "SELECT a.ACCOUNT_NUMBER " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
            return jdbcTemplate.queryForList(sql, new Object[]{customerId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new PayeeException("No account found for customer ID: " + customerId);
        }
    }


    //method by UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findByUsername(username);
        if(customer==null)
            throw new UsernameNotFoundException(username);
        return customer;
    }


}