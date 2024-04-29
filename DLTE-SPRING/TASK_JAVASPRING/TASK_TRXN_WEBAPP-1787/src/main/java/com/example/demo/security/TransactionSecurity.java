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
            httpSecurity.formLogin();

            httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();

            //Authority
            httpSecurity.authorizeRequests().antMatchers("/transaction").permitAll();
            httpSecurity.authorizeRequests().antMatchers("/transaction/new").permitAll();
            httpSecurity.authorizeRequests().antMatchers("/transaction/filter").permitAll();
            httpSecurity.authorizeRequests().antMatchers("/transaction/find").permitAll();

//            httpSecurity.authorizeRequests().antMatchers("/transaction/new").hasAuthority("admin");
//            httpSecurity.authorizeRequests().antMatchers("/transaction//findby/{name}").permitAll();
//            httpSecurity.authorizeRequests().antMatchers("/transaction//findto/{nameto}").hasAuthority("customer");
//            httpSecurity.authorizeRequests().antMatchers("/transaction/findamount/{amount}").hasAuthority("customer");
//            httpSecurity.authorizeRequests().antMatchers("/transaction/update").hasAnyAuthority("manager", "admin");
//            httpSecurity.authorizeRequests().antMatchers("/transaction/delete/{date1}/{date2}").hasAuthority("admin");



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
