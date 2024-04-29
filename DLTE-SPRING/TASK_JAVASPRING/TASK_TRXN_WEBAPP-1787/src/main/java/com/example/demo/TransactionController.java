//package com.example.demo;//package com.jdbctemp.template;
//
//import com.example.demo.entity.Transaction;
//import com.example.demo.service.TransactionException;
//import com.example.demo.service.TransactionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
////Rest controller
//@RestController
//@RequestMapping("/transaction")
//public class TransactionController {
//    @Autowired
//    TransactionService transactionService;
//
//
//    // view all
//    @GetMapping("/all")
//    public List<Transaction> findall(){
//        List<Transaction> transactionList=null;
//        try{
//            transactionList=transactionService.apiFindAll();
//        }catch (TransactionException tr){
//            throw new TransactionException();
//        }
//        return transactionList;
//    }
//
//
//    @PostMapping("/new")
//    public String save(@RequestBody Transaction transaction){
//        Transaction transaction1=null;
//        try{
//            transaction1=transactionService.apiSave(transaction);
//        }
//        catch (TransactionException t){
//            throw new TransactionException();
//
//        }
//        return transaction1+" has transaction saved successfully";
//    }
//
//    // find transaction details by inputting sender name
//    @GetMapping("/findby/{name}")
//    public Optional<Transaction> findSender(@PathVariable("name") String name){
//        return transactionService.apiFindBySender(name);
//    }
//
//    //find transaction details by specifnn the receiver
//    @GetMapping("/findto/{nameto}")
//    public Optional<Transaction> findReceiver(@PathVariable("nameto") String nameto){
//        return transactionService.apiFindToReceiver(nameto);
//    }
//
//    //find transaction details by amount
//    @GetMapping("/findamount/{amount}")
//    public Optional<Transaction> findAmount(@PathVariable("amount") Double amount){
//        return transactionService.apiFindAmount(amount);
//    }
//
//    //update transaction details on remarks
//    @PutMapping("/update")
//    public Transaction updateRemarks(@RequestBody Transaction transaction){
//        return transactionService.updateOnRemark(transaction);
//    }
//
//    @DeleteMapping("/delete/{date1}/{date2}")
//    public String deleteRecord(@PathVariable ("date1") Date date1, @PathVariable ("date2") Date date2){
//        return transactionService.removeOnDate(date1,date2);
//    }
//}
