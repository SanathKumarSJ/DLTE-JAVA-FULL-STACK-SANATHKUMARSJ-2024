package com.jpa.hqlsql.jpahqlsql;

import com.jpa.hqlsql.jpahqlsql.entity.Transaction_JPA;
import com.jpa.hqlsql.jpahqlsql.service.TransactioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction/")
public class TransactionAPI {
    @Autowired
    TransactioService transactioService;

    @PostMapping("/new/")
    public String apiSave(@RequestBody Transaction_JPA transaction){
        return transactioService.callsave(transaction)+ "saved successfully";
    }

    @GetMapping("/{name}/{type}")
    public List<Transaction_JPA> apiType(@PathVariable("name") String name,@PathVariable("type") String type){
        return transactioService.callFindByNameType(name, type);
    }

    @GetMapping("/amount/{amountS}/{amountE}")
    public List<Transaction_JPA> apiAmount(@PathVariable("amountS") Double amount1,@PathVariable("amountE")  Double amount2){
        return transactioService.callFindAmount(amount1,amount2);
    }
}
