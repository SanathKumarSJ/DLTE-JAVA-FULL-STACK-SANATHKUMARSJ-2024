package com.jdbctemp.template.mvc;


import com.jdbctemp.template.entity.Transaction;
import com.jdbctemp.template.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionServices;

    //to select the operation i.e add new transaction
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // adding new transaction
    @GetMapping("/new")
    public String show(Model model){
        model.addAttribute("transaction",new Transaction());
        return "addTransaction";
    }

    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@ModelAttribute Transaction transaction, Model model){
        model.addAttribute("transaction",transaction);
        //calling save method
        Transaction newTransaction=transactionServices.apiSave(transaction);
        if(newTransaction!=null){
            model.addAttribute("message","Transaction is successful!");
            model.addAttribute("transaction",newTransaction);
            return "index";
        }else{
            model.addAttribute("message","Transaction is failed!");
            return "addTransaction";
        }
    }

}