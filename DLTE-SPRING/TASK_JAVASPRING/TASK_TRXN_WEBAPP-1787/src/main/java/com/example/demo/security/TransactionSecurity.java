package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TransactionSecurity {

    public class LoanSecurity {

        @Autowired
        private MyBankUsersService service;

        AuthenticationManager manager;

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf().disable();
            httpSecurity.httpBasic();
            httpSecurity.formLogin().loginPage("/transaction/").usernameParameter("username");

            httpSecurity.authorizeRequests().antMatchers("/web/**").permitAll();

            httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();

            //Authority
            httpSecurity.authorizeRequests().antMatchers("/transaction").permitAll();
            httpSecurity.authorizeRequests().antMatchers("/transaction/new").permitAll();
            httpSecurity.authorizeRequests().antMatchers("/transaction/filter").permitAll();
            httpSecurity.authorizeRequests().antMatchers("/transaction/find").permitAll();



            // 3rd layer
            AuthenticationManagerBuilder builder=httpSecurity.
                    getSharedObject(AuthenticationManagerBuilder.class);
            builder.userDetailsService(service);
            manager=builder.build();
            httpSecurity.authenticationManager(manager);

            return httpSecurity.build();
        }
    }
}
