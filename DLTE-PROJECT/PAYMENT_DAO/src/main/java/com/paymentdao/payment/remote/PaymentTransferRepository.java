package com.paymentdao.payment.remote;


import com.paymentdao.payment.entity.Payee;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface PaymentTransferRepository {
    List<Payee> findAllPayee(Long accountNumber) throws SQLSyntaxErrorException;
    String addNewPayee(Payee payee);
//    String addNewPayee(Payee payee,Long senderAccountNumber);
}
