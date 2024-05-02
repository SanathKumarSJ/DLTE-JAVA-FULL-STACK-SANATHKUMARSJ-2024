package com.payment.webservice.mvc;

import com.payment.webservice.controller.MyController;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.MyBankOfficialsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.payee.Payee;

import javax.validation.Valid;

@Controller
@RequestMapping("/payee")
public class PaymentModelController {
    org.slf4j.Logger logger = LoggerFactory.getLogger(PaymentModelController.class);
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
}