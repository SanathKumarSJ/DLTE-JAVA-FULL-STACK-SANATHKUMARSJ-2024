package com.payment.webservice.security;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class MyBankOfficialsAPI {
    @Autowired
    MyBankOfficialsService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Customer save(@RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return service.signingUp(customer);
    }
}