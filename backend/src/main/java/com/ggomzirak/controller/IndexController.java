package com.ggomzirak.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ggomzirak.dto.SessionUser;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
//        model.addAttribute("posts", postsService.findAllDesc());
        
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}