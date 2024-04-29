package com.restcontroller.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/loan")
public class MyServlet {
    ArrayList arrayList = new ArrayList(Arrays.asList(1,21));
    List<Loan> loansList;
    public MyServlet() {
        arrayList.add(1);
        loansList= Stream.of(new Loan(987455645L,25000L,"close","personal","Sanath",9745896321L),new Loan(7415855454545L,78000L,"open","personal","Rohith",9845564566666L),new Loan(78798454165L,96000L,"open","home","Mahesh",84654656664564L)).collect(Collectors.toList());
        System.out.println(arrayList);
    }

    //get index from server and return the loan stored in that index
    @GetMapping("/get/{index}")
    public Loan readByIndex(@PathVariable("index") Integer index){
        return loansList.get(index);
    }

    //adding new loan
    @PostMapping("/add")
    public String addNewLoan(@RequestBody Loan loan){
        loansList.add(loan);
        return loan+"added successfully";
    }
    @GetMapping("/c")
    public String list(){
        return "hi there";
    }
}
