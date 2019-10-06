package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /**
     * ログインが成功した場合、このメソッドが呼び出される。
     */
    @RequestMapping("/")
    public String login(Model model) {
        //メインページ。
        return "index";
    }

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@PostMapping("/login")
	public String userAuth() {
		return "redirect:/login-error";
	}

	@PostMapping("/authenticate")
	public String authUser() {
		return "redirect:/login-error";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
}
