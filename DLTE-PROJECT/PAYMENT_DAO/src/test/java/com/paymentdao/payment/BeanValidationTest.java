package com.paymentdao.payment;

import com.paymentdao.payment.configuration.ValidationConfiguration;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.service.MyBankOfficialsService;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ResourceBundle;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BeanValidationTest {

    @InjectMocks
    ValidationConfiguration validationConfiguration;

    private Validator validator;

    @BeforeEach
    void setup() {
        LocalValidatorFactoryBean validatorFactoryBean = validationConfiguration.getValidator(validationConfiguration.messageSource());
        validatorFactoryBean.afterPropertiesSet();
        validator = validatorFactoryBean.getValidator();
    }


    @Test
    void validatePayeeAcc() {
        Payee payee = new Payee();
        payee.setPayeeId(100);
        payee.setSenderAccountNumber(741852963741L);
        payee.setPayeeAccountNumber(null);
        payee.setPayeeName("Sanath");

        Set<ConstraintViolation<Payee>> violations = validator.validate(payee);
        //[ConstraintViolationImpl{interpolatedMessage='{payee.payeeAcc}', propertyPath=payeeAccountNumber, rootBeanClass=class com.paymentdao.payment.entity.Payee, messageTemplate='{payee.payeeAcc}'}, ConstraintViolationImpl{interpolatedMessage='{payee.holder}', propertyPath=payeeName, rootBeanClass=class com.paymentdao.payment.entity.Payee, messageTemplate='{payee.holder}'}]
        //System.out.println(violations);

        assertEquals("EXB001 :Enter Valid Payee Account Number", violations.iterator().next().getMessage());
    }

    @Test
    void validatePayeeAcc2() {
        Payee payee = new Payee();
        payee.setPayeeAccountNumber(798456456L);
        payee.setPayeeName("Sanath");

        Set<ConstraintViolation<Payee>> violations = validator.validate(payee);
        assertEquals("EXB001 :Enter Valid Payee Account Number", violations.iterator().next().getMessage());
    }


    @Test
    void validatePayeeName() {
        Payee payee = new Payee();
        payee.setPayeeAccountNumber(789456123123L);
        payee.setPayeeName("Akash456");

        Set<ConstraintViolation<Payee>> violations = validator.validate(payee);
        assertEquals("EXB002 :Enter Valid Payee Name", violations.iterator().next().getMessage());
    }

}
