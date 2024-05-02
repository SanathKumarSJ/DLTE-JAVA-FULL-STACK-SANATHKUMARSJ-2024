package com.example.demo.mvc;
import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/transaction")
public class MyController {

    Logger logger= LoggerFactory.getLogger(MyController.class);
    @Autowired
    TransactionService transactionServices;

    //index page
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/display")
    public String display(Model model){
        model.addAttribute("transaction",new Transaction());
        return "dash";
    }

    @RequestMapping(value="/dash", method = RequestMethod.POST)
    public String homePage(){
        return "dash";
    }

    @GetMapping("/new")
    public String show(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "addTransaction";
    }

    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult bindingResult, Model model){
        model.addAttribute("transaction",transaction);
        if(!bindingResult.hasErrors()){
            Transaction transaction1=transactionServices.apiSave(transaction);
            model.addAttribute("message","Transaction is successful!");
            model.addAttribute("transaction",transaction1);
            return "dash";
        }else{
            model.addAttribute("message","Transaction is failed!");
            return "addTransaction";
        }
    }

    @GetMapping("/find")
    public String searchShow(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "findTransaction";
    }

    @GetMapping("/filter")
    public String search(@RequestParam("filterBasedOn") String filterBasedOn, @RequestParam("search") String search,Model model){
        Optional<Transaction> transactionList=null;
        System.out.println(search);
    logger.info(search);
        if(filterBasedOn.equalsIgnoreCase("Sender"))
            transactionList=transactionServices.apiFindBySender(search);
        else if(filterBasedOn.equalsIgnoreCase("Receiver"))
            transactionList=transactionServices.apiFindToReceiver(search);
        else {
            transactionList=transactionServices.apiFindAmount(Double.parseDouble(search));
        }
        model.addAttribute("transactions",transactionList);
        return "findTransaction";
    }

    @GetMapping("/remove")
    public String deleteShow(Model model){
        model.addAttribute("transaction",new Transaction());
        return "delete";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("startDate") String startDate1,@RequestParam("endDate") String endDate1,Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = (Date) dateFormat.parse(startDate1);
            endDate = (Date) dateFormat.parse(endDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String delete = transactionServices.removeOnDate(startDate, endDate);
        model.addAttribute("messageDelete", delete);
        return "index";
    }
        @PostMapping("/logout")
        public String logout() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
            return "redirect:/index";
        }
    }