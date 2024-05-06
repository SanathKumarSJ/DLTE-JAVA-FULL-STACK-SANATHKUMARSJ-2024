package com.payment.webservice.mvc;

import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payee")
public class PaymentModelViewController {
    org.slf4j.Logger logger = LoggerFactory.getLogger(PaymentModelViewController.class);
    @Autowired
    PaymentTransferRepository paymentTransferRepository;

    @Autowired
    MyBankOfficialsService service;

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }


    @PostMapping("/")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "index";
    }
    @GetMapping("/dashboard")
    public String homePage(){
        return "dash";
    }

    @GetMapping("/view")
    public String viewPage(){
        return "viewPayee";
    }

    @GetMapping("/add")
    public String show(Model model){
        return "addpayee";
    }

    @GetMapping("/error")
    public String errorPage(){
        return "error";
    }


    @GetMapping("/username")
    @ResponseBody
    public String customerName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return service.findByUsername(name).getCustomerName();
    }
}