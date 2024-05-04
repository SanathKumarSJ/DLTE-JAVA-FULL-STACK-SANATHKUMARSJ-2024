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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();
        httpSecurity.formLogin().loginPage("/payee/").
                usernameParameter("username").
                failureHandler(myBankFailureHandler).
                successHandler(myBankSuccessHandler);

        httpSecurity.authorizeRequests().antMatchers("/payee/**").permitAll();




        httpSecurity.authorizeRequests().antMatchers("/scripts/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/images/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/css/**").permitAll();

        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.logout().permitAll();
        httpSecurity.csrf().disable();

        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(service);
        manager = builder.build();
        httpSecurity.authenticationManager(manager);
        return httpSecurity.build();

    }
}
