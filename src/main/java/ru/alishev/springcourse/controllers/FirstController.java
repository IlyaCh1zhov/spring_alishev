package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value ="surname", required = false) String surname,
                            Model model){
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a") int a,
                             @RequestParam(value = "b") int b,
                             @RequestParam(value = "action") String action,
                             Model model){
        double result = 0;

        switch (action){
            case ("multiplication"):
                result = a * b;
                break;
            case ("addition"):
                result = a + b;
                break;
            case ("subtraction"):
                result = a - b;
                break;
            case ("division"):
                result = a / (double) b;
                break;
        }
        model.addAttribute("action", action);
        model.addAttribute("result", result);
        return "first/calculator";
    }
}
