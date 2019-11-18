package com.example.controller;

import static com.example.common.PathConst.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.exception.CustomServiceException;
import com.example.form.SessionForm;
import com.example.form.UserInfoForm;
import com.example.model.MRole;
import com.example.model.MTeam;
import com.example.model.MUser;
import com.example.service.UserInfoService;
import com.example.service.UserListService;

/**
 * ユーザー情報 Controller
 */
@Controller
@RequestMapping(USER_LIST_PAGE)
public class UserListController {

	public static final String HTML_PATH = USER_LIST_PAGE;

	/**
	 * セッション情報持ち回り用フォームクラス(セッションスコープ)
	 */
	@Autowired
	SessionForm session;

    @Autowired
    UserInfoService userInfoService;

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
		;
	    MUser mUser = userInfoService.getUser(session.getUserId());
		model.addAttribute("roleId", mUser.getRoleId());
        // チームリストを取得
        List<MTeam> teamList = userInfoService.getTeamList();
        model.addAttribute("teamList", teamList);
        List<MRole> roleList = userInfoService.getRoleList();
        model.addAttribute("roleList", roleList);
        model.addAttribute("userInfoForm", new UserInfoForm());
        model.addAttribute("mUserList", new ArrayList<MUser>());
		List<MUser> userlist = userListService.searchAll();
		model.addAttribute("userlist", userlist);
		return HTML_PATH;
		}

    /**
    *
    * ユーザーの新規登録。
    * @param model
    * @param form
    * @return
    */
   @RequestMapping(value = "/confirm", method = RequestMethod.POST)
   public String confirm(Model model, UserInfoForm form) {
	    MUser mUser = userInfoService.getUser(session.getUserId());
		model.addAttribute("roleId", mUser.getRoleId());
       // formをmodelに詰める
       List<MTeam> teamList = userInfoService.getTeamList();
       model.addAttribute("teamList", teamList);
       List<MRole> roleList = userInfoService.getRoleList();
       model.addAttribute("roleList", roleList);
       model.addAttribute("userInfoForm", new UserInfoForm());

       // ログインIDを取得
       String loginId = form.getLoginId();
       // ログインIDでユーザー情報マスタを検索
       mUser = userInfoService.getUser(loginId);

       // 重複するログインIDが存在する
       if(mUser != null) {
           // 重複確認結果をmodelに詰める
           model.addAttribute("isDuplicate", true);
           // 入力画面へ遷移
           return HTML_PATH;
       }

       // 重複するログインIDが存在しない
       // 重複確認結果をmodelに詰める
       model.addAttribute("isDuplicate", false);

       // DBに登録
       try {
           userInfoService.registUser(form);
       } catch (CustomServiceException e) {
           e.printStackTrace();
           // 登録結果=error
           model.addAttribute("result", "error");
           // 完了画面へ遷移
           return HTML_PATH;
       }

       // 登録結果=success
       model.addAttribute("result", "success");
		List<MUser> userlist = userListService.searchAll();
		model.addAttribute("userlist", userlist);
       // 完了画面へ遷移
       return HTML_PATH;
   }

   /**
   *
   * ユーザーの編集。
   * @param model
   * @param form
   * @return
   */
   @RequestMapping(value = "/edit", method = RequestMethod.POST)
   public String edit(Model model, UserInfoForm form) {
	    MUser mUser = userInfoService.getUser(session.getUserId());
		model.addAttribute("roleId", mUser.getRoleId());
       // formをmodelに詰める
       List<MTeam> teamList = userInfoService.getTeamList();
       model.addAttribute("teamList", teamList);
       List<MRole> roleList = userInfoService.getRoleList();
       model.addAttribute("roleList", roleList);
       model.addAttribute("userInfoForm", new UserInfoForm());

       // DBに登録
       try {
           userInfoService.updateUser(form);
       } catch (CustomServiceException e) {
           e.printStackTrace();
           // 登録結果=error
           model.addAttribute("result", "error");
           // 完了画面へ遷移
           return HTML_PATH;
       }

       // 登録結果=success
       model.addAttribute("result", "success");
		List<MUser> userlist = userListService.searchAll();
		model.addAttribute("userlist", userlist);
       // 完了画面へ遷移
       return HTML_PATH;
   }

   /**
   *
   * ユーザーの削除。
   * @param model
   * @param form
   * @return
   */
   @RequestMapping(value = "/delete", method = RequestMethod.POST)
   public String delete(Model model, UserInfoForm form) {
	    MUser mUser = userInfoService.getUser(session.getUserId());
		model.addAttribute("roleId", mUser.getRoleId());
       // formをmodelに詰める
       List<MTeam> teamList = userInfoService.getTeamList();
       model.addAttribute("teamList", teamList);
       List<MRole> roleList = userInfoService.getRoleList();
       model.addAttribute("roleList", roleList);
       model.addAttribute("userInfoForm", new UserInfoForm());

       //削除したいユーザーのユーザーIDを入れる
//       form.setUserId("3");
       // DBに登録
       try {
           userInfoService.deleteUser(form);
       } catch (CustomServiceException e) {
           e.printStackTrace();
           // 登録結果=error
           model.addAttribute("result", "error");
           // 完了画面へ遷移
           return HTML_PATH;
       }

       // 登録結果=success
       model.addAttribute("result", "success");
		List<MUser> userlist = userListService.searchAll();
		model.addAttribute("userlist", userlist);
       // 完了画面へ遷移
       return HTML_PATH;
   }

   /**
   *
   * ユーザーの検索。
   * @param model
   * @param form
   * @return
   */
   @RequestMapping(value = "/usersearch", method = RequestMethod.POST)
   public String userSearch(Model model, UserInfoForm form) {
	   	MUser mUser = userInfoService.getUser(session.getUserId());
		model.addAttribute("roleId", mUser.getRoleId());
		List<MTeam> teamList = userInfoService.getTeamList();
       model.addAttribute("teamList", teamList);
       List<MRole> roleList = userInfoService.getRoleList();
       model.addAttribute("roleList", roleList);
       model.addAttribute("userInfoForm", new UserInfoForm());

       String userName = form.getUserName();

       List<MUser>mUserList= userInfoService.searchUser(userName);

       model.addAttribute("mUserList",mUserList);
		List<MUser> userlist = userListService.searchAll();
		model.addAttribute("userlist", mUserList);
       return HTML_PATH;
   }
}