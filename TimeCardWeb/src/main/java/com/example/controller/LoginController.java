package com.example.controller;

import static com.example.common.PathConst.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
////LOGIN_PAGE
//@RequestMapping("/")
@RestController()
@RequestMapping("/api")
public class LoginController {

	public static final String HTML_PATH = LOGIN_PAGE;
//    @RequestMapping
//    public String index(Model model) {
//        return "/index";
//    }


    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    public String redirectApi() {
        return "forward:/";
    }
}
