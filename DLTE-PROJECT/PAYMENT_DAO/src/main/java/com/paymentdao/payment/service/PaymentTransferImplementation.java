package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.exception.PayeeExistException;
import com.paymentdao.payment.exception.PayeeNotExistException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
            payees= jdbcTemplate.query("select PAYEE_ID,SENDER_ACCOUNT_NUMBER,PAYEE_ACCOUNT_NUMBER,PAYEE_NAME from MYBANK_APP_PAYEE where sender_account_number=?", new Object[]{accountNumber}, new PayeeMapper());

        if(payees.size()==0){
            logger.info(resourceBundle.getString("no.payee"));
            throw new PayeeException(resourceBundle.getString("no.payee")+" "+accountNumber);
        }
        logger.info(resourceBundle.getString("yes.payee"));
        return payees;
    }



    //------------------------------------------ADD NEW PAYEE ----------------------------------------------

    @Override
    public String addNewPayee(Payee payee) {
        try {
            String sql = "{call UPDATE_PAYEE_DETAILS(PAYEE_SEQ.nextval, ?, ?, ?)}";
            jdbcTemplate.update(sql, payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());

        } catch (DataAccessException e) {

            Throwable cause = e.getCause();
//            System.out.println(cause);
            //specific type of exception that represents errors reported by the database
//            logger.info("--->>>"+e.getLocalizedMessage()+"  "+cause);
            if (cause instanceof SQLException) {
                //SQLException object so that we can access its properties
                SQLException sqlException = (SQLException) cause;

                if (sqlException.getErrorCode() == 20002) {
                    logger.error(resourceBundle.getString("no.payee.acc"));
                    throw new PayeeNotExistException(resourceBundle.getString("no.payee.acc"));
                }
                else if (sqlException.getErrorCode() == 20003) {
                    logger.error(resourceBundle.getString("input.duplicate"));
                    throw new PayeeException(resourceBundle.getString("input.duplicate"));
                }
                else if (sqlException.getErrorCode() == 20004) {
                    logger.error(resourceBundle.getString("payee.exist"));
                    throw new PayeeExistException(resourceBundle.getString("payee.exist"));
                }
            }
        }
        logger.info(resourceBundle.getString("insert.ok"));
        return resourceBundle.getString("insert.ok");
    }





    public static class PayeeMapper implements RowMapper<Payee> {
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