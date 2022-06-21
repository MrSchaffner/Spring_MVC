package com.pluralsight.conference2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingContrller {

//    @GetMapping("greeting")
//    public String greeting(Map<String, Object> model){
//        model.put("message1", "hello Seth from greetingMethod()"); //var name from jsp page
//        return "greeting";
//
//    }

    @GetMapping("greeting")
    public String greetingMethod(Map<String, Object> model){
        model.put("message1", "hello Seth from greetingMethod()"); //var name from jsp page
        String myMessage = "greeting";
        return myMessage;

    }

}
