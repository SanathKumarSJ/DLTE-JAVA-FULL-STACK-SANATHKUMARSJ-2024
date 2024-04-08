package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Service
public class PaymentTransferImplementation implements PaymentTransferRepository {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("payee");
    Logger logger= LoggerFactory.getLogger(PaymentTransferImplementation.class);
    @Autowired
    JdbcTemplate jdbcTemplate ;


    @Override
    public List<Payee> findAllPayee(Long accountNumber) throws SQLSyntaxErrorException {
        List<Payee> payees;


            payees= jdbcTemplate.query("select PAYEE_ID,SENDER_ACCOUNT_NUMBER,PAYEE_ACCOUNT_NUMBER,PAYEE_NAME from MYBANK_APP_PAYEE where sender_account_number=?",
                    new Object[]{accountNumber},
                    new PayeeMapper());

        if(payees.size()==0){
            logger.info(resourceBundle.getString("no.payee"));
            throw new PayeeException(resourceBundle.getString("no.payee")+" "+accountNumber);
        }
        else
        {
            logger.info(resourceBundle.getString("yes.payee"));
        }
        return payees;
    }


    //----------------fetch all the payee record-----------------------------
    @Override
    public List<Payee> fetchAllPayeeDetails() throws SQLSyntaxErrorException {
        List<Payee> payees;
        try {
            payees= jdbcTemplate.query("select PAYEE_ID,SENDER_ACCOUNT_NUMBER,PAYEE_ACCOUNT_NUMBER,PAYEE_NAME from MYBANK_APP_PAYEE",
//                    new Object[]{accountNumber},
                    new PayeeMapper());
        }catch (DataAccessException sqlException){
            logger.warn(resourceBundle.getString("payee.fail"));
            throw new SQLSyntaxErrorException();
        }
        if(payees.size()==0){
            logger.info(resourceBundle.getString("no.payee"));
            throw new PayeeException(resourceBundle.getString("no.payee"));
        }
        else
        {
            logger.info(resourceBundle.getString("yes.payee"));
        }
        return payees;
    }



    public class PayeeMapper implements RowMapper<Payee> {
        @Override
        public Payee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payee payee=new Payee();
            payee.setPayeeId(rs.getInt("PAYEE_ID"));
            payee.setSenderAccountNumber(rs.getLong("SENDER_ACCOUNT_NUMBER"));
            payee.setPayeeAccountNumber(rs.getLong("PAYEE_ACCOUNT_NUMBER"));
            payee.setPayeeName(rs.getString("PAYEE_NAME"));
            return payee;
        }
    }
}
