package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.MUser;
import com.example.service.UserListService;

/**
 * ユーザー情報 Controller
 */
@Controller
public class UserListController {
	/**
	 * * ユーザー情報 Service
	 * */
	@Autowired
	private UserListService userListService;

	/**
	 * * ユーザー情報一覧画面を表示
	 * * @param model Model
	 * * @return ユーザー情報一覧画面
	 * */
	@GetMapping(value = "/user/list")
	public String displayList(Model model) {
		List<MUser> userlist = userListService.searchAll();
		model.addAttribute("userlist", userlist);
		return "user/list";
		}
}