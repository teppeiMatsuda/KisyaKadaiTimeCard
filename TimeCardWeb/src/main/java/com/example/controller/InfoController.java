package com.example.controller;

import static com.example.common.PathConst.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(INFO_PAGE)
public class InfoController {
	public static final String HTML_PATH = INFO_PAGE;
    @RequestMapping
    public String index(Model model) {
        return HTML_PATH;
    }


    @RequestMapping(value = "/attendance", method = RequestMethod.POST)
    @ResponseBody
    public String getSelectData(String aButtonInfo) {


    	System.out.println(aButtonInfo);


        return "値を返します！！！";
    }


}
