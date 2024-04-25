package com.payment.webservice.security;

import com.paymentdao.payment.service.MyBankOfficialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class PaymentSecurity {

        @Autowired
        private MyBankOfficialsService service;

        AuthenticationManager manager;

        @Autowired
        MyBankFailureHandler myBankFailureHandler;
        @Autowired
        MyBankSuccessHandler myBankSuccessHandler;

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

            httpSecurity.httpBasic();
            httpSecurity.formLogin().
                    usernameParameter("username").
                    failureHandler(myBankFailureHandler).
                    successHandler(myBankSuccessHandler);
            httpSecurity.csrf().disable(); //Cross-Site Request Forgery to avoid  unintended requests to a web application.
            httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
            httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
            httpSecurity.authorizeRequests().anyRequest().authenticated();


            AuthenticationManagerBuilder builder=httpSecurity.
                    getSharedObject(AuthenticationManagerBuilder.class);
            builder.userDetailsService(service);
            manager=builder.build();
            httpSecurity.authenticationManager(manager);
            return httpSecurity.build();
        }


}

