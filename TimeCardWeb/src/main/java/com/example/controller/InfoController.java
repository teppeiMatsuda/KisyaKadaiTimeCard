package com.example.controller;

import static com.example.common.PathConst.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.exception.CustomServiceException;
import com.example.service.InfoService;


@Controller
@RequestMapping(INFO_PAGE)
public class InfoController {

	public static final String HTML_PATH = INFO_PAGE;

	/**
	 * ログイン後に表示されるメインページのサービスクラスです。
	 */
	@Autowired
	InfoService infoService;

    @RequestMapping
    public String index(Model model, UsernamePasswordAuthenticationToken principal) {

    	try {
    		// 初期表示用に必要な情報を取得するサービスのメソッドを呼び出し。
			infoService.initialDisplay(principal);
		} catch (CustomServiceException e) {
			// 業務例外をキャッチ
			e.printStackTrace();
			return HTML_PATH;
		}

    	//メインページ表示の時点でユーザーIDなどをセッション情報に含める算段です。
    	//ユーザーID以外の基本情報に関しては→UsernamePasswordAuthenticationToken principalに入っているはず。
    	//現状は所属チーム情報などは当て回らないつもりですが
    	//予想以上にアクセス頻度が高く、入っていないと不便な情報についてはこちらのメソッドに処理を追加してください。

        return HTML_PATH;
    }


    @RequestMapping(value = "/attendance", method = RequestMethod.POST)
    @ResponseBody
    public String getSelectData(String aButtonInfo) {


    	System.out.println(aButtonInfo);


        return "値を返します！！！";
    }


}
