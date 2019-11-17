package com.example.controller;

import static com.example.common.PathConst.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.MUser;
import com.example.service.UserListService;

/**
 * ユーザー情報 Controller
 */
@Controller
@RequestMapping(USER_LIST_PAGE)
public class UserListController {

	public static final String HTML_PATH = USER_LIST_PAGE;

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
	@RequestMapping
	public String displayList(Model model	) {
		List<MUser> userlist = userListService.searchAll();
		model.addAttribute("userlist", userlist);
		return HTML_PATH;
		}
}