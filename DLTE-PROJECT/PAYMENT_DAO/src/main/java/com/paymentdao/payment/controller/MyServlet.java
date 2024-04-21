//package com.paymentdao.payment.controller;
//
//import com.paymentdao.payment.entity.Payee;
//import com.paymentdao.payment.exception.PayeeException;
//import com.paymentdao.payment.remote.PaymentTransferRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.SQLSyntaxErrorException;
//import java.text.ParseException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/payee")
//public class MyServlet {
//    @Autowired
//    PaymentTransferRepository paymentTransferRepository;
//
//    @PostMapping("/add")
//    public String newPayee(@RequestBody Payee payee){
//        try {
//            String check = paymentTransferRepository.addNewPayee(payee);
//        }catch (PayeeException e){
//            return e.getMessage();
//        }
//        return "Inserted";
//    }
//
//    @GetMapping("get/{acc}")
//    public List<Payee> list(@PathVariable ("acc")Long acc) throws SQLSyntaxErrorException {
//        return paymentTransferRepository.findAllPayee(acc);
//    }
//    @GetMapping("/g")
//    public String list2(){
//        return "hi there";
//    }
//}
