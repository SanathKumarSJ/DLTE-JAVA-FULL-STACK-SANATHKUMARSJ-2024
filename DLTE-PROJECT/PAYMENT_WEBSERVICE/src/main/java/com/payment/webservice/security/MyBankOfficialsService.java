package com.payment.webservice.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankOfficialsService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger= LoggerFactory.getLogger(MyBankOfficialsService.class);


    public MyBankOfficials signingUp(MyBankOfficials myBankOfficials){
        int ack = jdbcTemplate.update("insert into MYBANK_APP_OFFICIALS values(?,?,?,?,?,?,?)",new Object[]{
                myBankOfficials.getUserId(),myBankOfficials.getName(),myBankOfficials.getUserName(),myBankOfficials.getPassword(),
                myBankOfficials.getEmail(),myBankOfficials.getStatus(),myBankOfficials.getAttempts()
        });
        return myBankOfficials;
    }

    public MyBankOfficials findByUsername(String username){
        MyBankOfficials myBankOfficials = jdbcTemplate.queryForObject("select * from MYBANK_APP_OFFICIALS where USERNAME=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankOfficials.class));
        return myBankOfficials;
    }

    public void updateAttempts(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update MYBANK_APP_OFFICIALS set ATTEMPTS=? where USERNAME=?",
                new Object[]{myBankOfficials.getAttempts(),myBankOfficials.getUsername()});
        logger.info("Attempts are updated");
    }

    public void updateStatus(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update MYBANK_APP_OFFICIALS set STATUS=0 where username=?",
                new Object[]{myBankOfficials.getUsername()});
        logger.info("Status has changed");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankOfficials officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}