package com.example.controller;

import static com.example.common.PathConst.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(LOGIN_PAGE)
public class LoginController {

	public static final String HTML_PATH = LOGIN_PAGE;
    @RequestMapping
    public String index(Model model) {
        return HTML_PATH;
    }
}
