package com.cong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @GetMapping("/toActivitySquare")
    public String toDashboard() {
        return "home_page/activitySquare";
    }
}
