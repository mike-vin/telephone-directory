package com.epam.telephonedirectory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String core() {
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {

        return "<H1 align=\"center\">H E L L O !</H1>";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "creator") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}