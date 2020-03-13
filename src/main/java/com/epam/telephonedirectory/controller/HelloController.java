package com.epam.telephonedirectory.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String core() {

        return "<H1 align=\"center\">H E L L O !</H1>";
    }

}