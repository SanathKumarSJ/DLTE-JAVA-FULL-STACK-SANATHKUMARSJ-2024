package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayeeController {
    @GetMapping("/")
    public String dashboard() {
        return "dash";
    }

    @GetMapping("/view")
    public String viewAll() {
        return "view";
    }

    @GetMapping("/add")
    public String addPayee() {
        return "addPayee";
    }
}
