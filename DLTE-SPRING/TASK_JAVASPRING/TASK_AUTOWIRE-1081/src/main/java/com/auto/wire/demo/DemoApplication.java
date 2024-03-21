package com.auto.wire.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(DemoApplication.class);
        applicationContext.scan("com.auto.wire.demo");
//            applicationContext.refresh();

        //Mybank execution --> home loan injection
        MyBank myBank=applicationContext.getBean(MyBank.class);
//        System.out.println("hello");
        System.out.println(myBank.callFindLoan().toString());
    }

}
