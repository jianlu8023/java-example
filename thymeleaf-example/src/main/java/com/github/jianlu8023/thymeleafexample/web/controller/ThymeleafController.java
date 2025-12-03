package com.github.jianlu8023.thymeleafexample.web.controller;

import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
public class ThymeleafController {
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("msg", "hello world");
        return "index";
    }
}
