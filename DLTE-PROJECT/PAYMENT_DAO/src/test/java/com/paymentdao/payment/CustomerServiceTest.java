package com.paymentdao.payment;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.service.MyBankOfficialsService;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("payee");

    @InjectMocks
    MyBankOfficialsService myBankOfficialsService;

    @Mock
    JdbcTemplate jdbcTemplate;



    @Test
    void testSigningUp() {
        Customer customer = new Customer(123L,"sanath","sringeri","active",8745213698L,"san","san123",1);

        when(jdbcTemplate.update(anyString(),eq(customer.getCustomerName()),eq(customer.getCustomerAddress()),eq(customer.getCustomerStatus()),eq(customer.getCustomerContact()),eq(customer.getUserName()),eq(customer.getPassword()))).thenReturn(1);

        Customer result = myBankOfficialsService.signingUp(customer);

        assertEquals(customer.getCustomerId()+customer.getAttempts(),result.getCustomerId()+customer.getAttempts());
        assertEquals(customer, result);
    }

    @Test
    void testSigningUpUserDetails() {
        Customer customer = new Customer(123L,"sanath","sringeri","active",8745213698L,"san","san123",1);

        when(jdbcTemplate.update(anyString(),eq(customer.getCustomerName()),eq(customer.getCustomerAddress()),eq(customer.getCustomerStatus()),eq(customer.getCustomerContact()),eq(customer.getUserName()),eq(customer.getPassword()))).thenReturn(1);

        Customer result = myBankOfficialsService.signingUp(customer);

        assertEquals(customer.getAuthorities(),result.getAuthorities());
        assertEquals(customer.isAccountNonExpired(),result.isAccountNonExpired());
        assertEquals(customer.isAccountNonLocked(),result.isAccountNonLocked());
        assertEquals(customer.isCredentialsNonExpired(),result.isCredentialsNonExpired());
        assertEquals(customer.isEnabled(),result.isEnabled());
    }
    @Test
    void testFindAllUsername() {
        Customer customer = new Customer(123L,"sanath","sringeri","active",8745213698L,"san","san123",1);
        Customer customer2 = new Customer(125L,"akash","kerla","active",78445213698L,"akash","aka123",1);

        List<Customer> customerList = Stream.of(customer,customer2).collect(Collectors.toList());


        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(customerList);

        List<Customer> result = myBankOfficialsService.findAllUsername();

        assertEquals(customerList.get(0).getUserName(), result.get(0).getUserName());

        assertNotEquals(customerList.get(0).getAttempts(),result.get(0).getMaxAttempt());
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class));
    }

    @Test
    void testFindByUsernameNotExistingCustomer() {
        Customer customer = new Customer(123L,"sanath","sringeri","active",8745213698L,"san","san123",1);
        Customer customer2=new Customer();
        customer2.setCustomerId(147L);
        customer2.setCustomerName("akash");
        customer2.setCustomerAddress("kerla");
        customer2.setCustomerStatus("active");
        customer2.setCustomerContact(78445213698L);
        customer2.setUserName("aka");
        customer2.setPassword("aka123");
        customer2.setAttempts(1);

        List<Customer> customerList = Stream.of(customer,customer2).collect(Collectors.toList());

        when(myBankOfficialsService.findAllUsername()).thenReturn(customerList);


        assertThrows(UsernameNotFoundException.class, () -> myBankOfficialsService.findByUsername("san"));

    }

    @Test
    void testUpdateAttempts() {
        Customer customer = new Customer(123L,"sanath","sringeri","active",8745213698L,"san","san123",1);


        when(jdbcTemplate.update(anyString(), eq(customer.getAttempts()),eq(customer.getUserName()))).thenReturn(1);


        myBankOfficialsService.updateAttempts(customer);


        verify(jdbcTemplate, times(1)).update(anyString(), eq(customer.getAttempts()),eq(customer.getUserName()));

    }


    @Test
    void testUpdateStatus() {
        Customer customer = new Customer(123L,"sanath","sringeri","active",8745213698L,"san","san123",1);


        when(jdbcTemplate.update(anyString(),eq(customer.getUserName()))).thenReturn(1);


        myBankOfficialsService.updateStatus(customer);


        verify(jdbcTemplate, times(1)).update(anyString(),eq(customer.getUserName()));

    }
    @Test
    void testGetAccountListSuccess() {
        Long customerId = 123L;
        List<Long> accountList = Collections.singletonList(456L);

        when(jdbcTemplate.queryForList(anyString(), any(Object[].class), eq(Long.class))).thenReturn(accountList);

        List<Long> result = myBankOfficialsService.getAccountList(customerId);

        assertNotNull(result);
        assertEquals(456L, result.get(0));

    }

    @Test
    void testGetAccountException(){
        Long customerId = 123L;
        when(jdbcTemplate.queryForList(anyString(), any(Object[].class), eq(Long.class))).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(PayeeException.class, () -> {
            myBankOfficialsService.getAccountList(customerId);
        });
    }

}
