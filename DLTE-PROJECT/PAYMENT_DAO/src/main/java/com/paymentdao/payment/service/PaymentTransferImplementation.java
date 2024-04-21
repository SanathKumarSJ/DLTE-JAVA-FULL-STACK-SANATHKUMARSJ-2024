package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.CollusionException;
import com.paymentdao.payment.exception.InactiveException;
import com.paymentdao.payment.exception.NotExistException;
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



    //------------------------------------------ADD NEW PAYEE ----------------------------------------------


    @Override
    public String addNewPayee(Payee payee) {
        try {
            // Prepare a CallableStatement to call the stored procedure
            String sql = "{call UPDATE_PAYEE_DETAILS(PAYEE_SEQ.nextval, ?, ?, ?)}";
            // Execute the stored procedure
            jdbcTemplate.update(sql, payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());

        } catch (DataAccessException e) {
            // Handle Spring DataAccessException
            Throwable cause = e.getCause();
//            System.out.println(cause);
            //specific type of exception that represents errors reported by the database
            if (cause instanceof SQLException) {
                //SQLException object so that we can access its properties
                SQLException sqlException = (SQLException) cause;
                // Check for specific SQL error code or SQL state
                if (sqlException.getErrorCode() == 20001) {
                    // Handle the specific exception (ORA-20001: No data found for sender account)
                    logger.error(resourceBundle.getString("no.send.acc"));
                    throw new NotExistException(resourceBundle.getString("no.sender.acc"));

                }
                else if (sqlException.getErrorCode() == 20002 ){
                    logger.error(resourceBundle.getString("no.status"));
                    throw new InactiveException(resourceBundle.getString("no.status"));
                }
                else if (sqlException.getErrorCode() == 20003 ){
                    logger.error(resourceBundle.getString("no.payee.acc"));
                    throw new NotExistException(resourceBundle.getString("no.payee.acc"));
                }
                else if (sqlException.getErrorCode() == 20005 ){
                    logger.error(resourceBundle.getString("payee.exist"));
                    throw new CollusionException(resourceBundle.getString("payee.exist"));
                }
                else if (sqlException.getErrorCode() == 20004 ){
                    logger.error(resourceBundle.getString("input.duplicate"));
                    throw new PayeeException(resourceBundle.getString("input.duplicate"));
                }

                //not the mentioned exception declaring it as procedure exception
            } else {
                // Handle other exceptions
                logger.error(resourceBundle.getString("procedure.error") + e.getMessage());
            }

            //acts as a catch-all for any exceptions that were not caught by the preceding catch
        } catch (Exception e) {
            // Handle any other exceptions
            logger.error(resourceBundle.getString("procedure.error") + e.getMessage());
        }

        logger.info(resourceBundle.getString("insert.ok"));
        return resourceBundle.getString("insert.ok");
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

























//    @Override
//    public String addNewPayee(Payee payee,Long senderAcc) {
//        try {
//            // Prepare a CallableStatement to call the stored procedure
//            String sql = "{call UPDATE_PAYEE_DETAILS(PAYEE_SEQ.nextval, ?, ?, ?)}";
//            // Execute the stored procedure
//            jdbcTemplate.update(sql, senderAcc, payee.getPayeeAccountNumber(), payee.getPayeeName());
//
//        } catch (DataAccessException e) {
//            // Handle Spring DataAccessException
//            Throwable cause = e.getCause();
////            System.out.println(cause);
//            //specific type of exception that represents errors reported by the database
//            if (cause instanceof SQLException) {
//                //SQLException object so that we can access its properties
//                SQLException sqlException = (SQLException) cause;
//                // Check for specific SQL error code or SQL state
//                if (sqlException.getErrorCode() == 20001) {
//                    // Handle the specific exception (ORA-20001: No data found for sender account)
//                    logger.error(resourceBundle.getString("no.send.acc"));
//                    throw new NotExistException(resourceBundle.getString("no.sender.acc"));
//
//                }
//                else if (sqlException.getErrorCode() == 20002 ){
//                    logger.error(resourceBundle.getString("no.status"));
//                    throw new InactiveException(resourceBundle.getString("no.status"));
//                }
//                else if (sqlException.getErrorCode() == 20003 ){
//                    logger.error(resourceBundle.getString("no.payee.acc"));
//                    throw new NotExistException(resourceBundle.getString("no.payee.acc"));
//                }
//                else if (sqlException.getErrorCode() == 20005 ){
//                    logger.error(resourceBundle.getString("payee.exist"));
//                    throw new CollusionException(resourceBundle.getString("payee.exist"));
//                }
//                else if (sqlException.getErrorCode() == 20004 ){
//                    logger.error(resourceBundle.getString("input.duplicate"));
//                    throw new PayeeException(resourceBundle.getString("input.duplicate"));
//                }
//
//                //not the mentioned exception declaring it as procedure exception
//            } else {
//                // Handle other exceptions
//                logger.error(resourceBundle.getString("procedure.error") + e.getMessage());
//            }
//
//            //acts as a catch-all for any exceptions that were not caught by the preceding catch
//        } catch (Exception e) {
//            // Handle any other exceptions
//            logger.error(resourceBundle.getString("procedure.error") + e.getMessage());
//        }
//
//        logger.info(resourceBundle.getString("insert.ok"));
//        return resourceBundle.getString("insert.ok");
//    }
//
//

