package com.github.ahisyfa.saad.saad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: HomeController.java, v 0.1 2023-07-16  09.05 Ahmad Isyfalana Amin Exp $
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }
}