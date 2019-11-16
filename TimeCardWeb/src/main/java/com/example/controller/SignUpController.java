package com.example.controller;

import static com.example.common.PathConst.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.exception.CustomServiceException;
import com.example.form.UserInfoForm;
import com.example.model.MTeam;
import com.example.model.MUser;
import com.example.service.UserInfoService;

@Controller
@RequestMapping(SIGNUP_PAGE)
public class SignUpController {

    public static final String HTML_PATH = SIGNUP_PAGE;

    @Autowired
    UserInfoService userInfoService;

    /**
     * ユーザー新規登録画面へ遷移。
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        // チームリストを取得
        List<MTeam> teamList = userInfoService.getTeamList();
        model.addAttribute("teamList", teamList);
        model.addAttribute("userInfoForm", new UserInfoForm());
//        MUser mUser =  userInfoService.getUser(3);
        model.addAttribute("userInfoForm", new UserInfoForm());
//        model.addAttribute("name", mUser.getUserName());
        return HTML_PATH;
    }

//    /**
//     *
//     * ユーザーの新規登録。
//     * @param model
//     * @param form
//     * @return
//     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(Model model, UserInfoForm form) {

        // formをmodelに詰める
        model.addAttribute("form", form);

        // ログインIDを取得
        String loginId = form.getLoginId();
        // ログインIDでユーザー情報マスタを検索
        MUser mUser = userInfoService.getUser(loginId);

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
        // 完了画面へ遷移
        return "/login";
    }
}
