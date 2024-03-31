package com.transaction.jdbc.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankUsersService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public MyBankUsers signingUp(MyBankUsers myBankUsers){
        int ack = jdbcTemplate.update("insert into MyBankUsers values(?,?,?,?,?,?,?)",new Object[]{
                //create table MyBankUsers(name varchar(255),username varchar(255), password varchar(255), contact number, email varchar(255),adhar number,role varchar(255));
                myBankUsers.getName(),myBankUsers.getUsername(),
                myBankUsers.getPassword(),myBankUsers.getContact(),myBankUsers.getEmail(),myBankUsers.getAdhar(),myBankUsers.getRole()
        });
        return myBankUsers;
    }

    public MyBankUsers findByUsername(String username){
        MyBankUsers myBankUsers = jdbcTemplate.queryForObject("select * from MyBankUsers where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers users = findByUsername(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }
}