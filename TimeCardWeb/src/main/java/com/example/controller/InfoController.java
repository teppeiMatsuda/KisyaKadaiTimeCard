package com.example.controller;

import static com.example.common.PathConst.INFO_PAGE;
import static com.example.common.PathConst.LOGIN_PAGE;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(INFO_PAGE)
public class InfoController {
	public static final String HTML_PATH = INFO_PAGE;
    @RequestMapping
    public String index(Model model) {
        return HTML_PATH;
    }

}
