package com.hid.resourceManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * controller for app's home page
 */
@Controller
public class Authentication {

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "Hello World");
        return "home";
    }
}
