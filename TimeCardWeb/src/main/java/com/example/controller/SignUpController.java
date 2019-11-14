package com.example.controller;

import static com.example.common.PathConst.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.MTeam;
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
        return HTML_PATH;
    }

//   20191109 五十嵐
//    以下、遷移に失敗するためコメントアウト。
//    原因がわかったら修正する。
//    /**
//     *
//     * 確認画面へ遷移。
//     * @param model
//     * @param form
//     * @return
//     */
//    @RequestMapping(value = "/confirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String confirm(Model model, @RequestBody UserInfoForm form) {
//
//        // formをmodelに詰める
//        model.addAttribute("form", form);
//
//        // ログインIDを取得
//        String loginId = form.getLoginId();
//        // ログインIDでユーザー情報マスタを検索
//        MUser mUser = userInfoService.getUser(loginId);
//
//        // 重複するログインIDが存在する
//        if(mUser != null) {
//            // 重複確認結果をmodelに詰める
//            model.addAttribute("isDuplicate", true);
//            // 入力画面へ遷移
//            return HTML_PATH;
//        }
//
//        // 重複するログインIDが存在しない
//        // 重複確認結果をmodelに詰める
//        model.addAttribute("isDuplicate", false);
//        // 確認画面へ遷移
//        return HTML_PATH + "/confirm";
//    }
//
//    /**
//     * ユーザー新規登録を実行後、完了画面へ遷移。
//     * @param form
//     * @return
//     */
//    @RequestMapping(value = "/regist", method = RequestMethod.POST)
//    public String regist(Model model, @RequestBody UserInfoForm form) {
//
//        // formをbeanに詰め替え
//        MUser mUser = new MUser();
//        TUserDetail tUserDetail = new TUserDetail();
//        BeanUtils.copyProperties(form, mUser);
//        BeanUtils.copyProperties(form, tUserDetail);
//
//        // DBに登録
//        try {
//            userInfoService.registUser(mUser, tUserDetail);
//        } catch (CustomServiceException e) {
//            e.printStackTrace();
//            // 登録結果=error
//            model.addAttribute("result", "error");
//            // 完了画面へ遷移
//            return HTML_PATH + "/regist";
//        }
//
//        // 登録結果=success
//        model.addAttribute("result", "success");
//        // 完了画面へ遷移
//        return HTML_PATH + "/regist";
//    }
}
