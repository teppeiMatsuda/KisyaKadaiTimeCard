package com.example.controller;

import static com.example.common.PathConst.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.exception.CustomServiceException;
import com.example.form.SessionForm;
import com.example.service.InfoService;

/**
 * ログイン後に表示されるメインページのコントローラーです。
 */
@Controller
@RequestMapping(INFO_PAGE)
public class InfoController {

	public static final String HTML_PATH = INFO_PAGE;

	/**
	 * セッション情報持ち回り用フォームクラス(セッションスコープ)
	 */
	@Autowired
	SessionForm session;

	/**
	 * ログイン後に表示されるメインページのサービスクラスです。
	 */
	@Autowired
	InfoService infoService;

	/**
	 * ログイン後に呼ばれる初期表示用のメソッドです。
	 * @param model モデル
	 * @param principal 認証情報
	 * @return htmlパス
	 */
    @RequestMapping
    public String index(Model model, UsernamePasswordAuthenticationToken principal) {
    	Map<String,Object> userStateMap = null;
    	try {
    		// 初期表示用に必要な情報を取得するサービスのメソッドを呼び出し。
    		userStateMap = infoService.initialDisplay(principal);
		} catch (CustomServiceException e) {
			// 業務例外をキャッチ
			e.printStackTrace();
			return HTML_PATH;
		}

		// 出退勤フラグとユーザーのロールIDを画面側に受け渡し。
		model.addAttribute("wStartFlg", (Boolean)userStateMap.get("wStartFlg"));
		model.addAttribute("wEndFlg", (Boolean)userStateMap.get("wEndFlg"));
		model.addAttribute("roleId",(String)userStateMap.get("roleId"));
    	//メインページ表示の時点でユーザーIDなどをセッション情報に含める算段です。
    	//ユーザーID以外の基本情報に関しては→UsernamePasswordAuthenticationToken principalに入っているはず。
    	//現状は所属チーム情報などは当て回らないつもりですが
    	//予想以上にアクセス頻度が高く、入っていないと不便な情報についてはこちらのメソッドに処理を追加してください。

		// セッション情報持ち回り用フォームBeanにユーザーID及び権限IDを設定。
		session.setUserId((Integer)userStateMap.get("userId"));

		// 開発時の確認用なので後で削除する ↓
		System.out.println("---状態マップ内容確認---");
		userStateMap.forEach((key, value) ->{
			System.out.println(key + ":" + value);
		}) ;
		System.out.println("---状態マップ内容確認終了---");
		// セッション内容確認
		System.out.println(session.toString());
		// 削除予定ここまで ↑

        return HTML_PATH;
    }

	/**
	 * クライアントからjsonでボタンのIDを受け取り出退勤どちらかの履歴を登録するメソッド。
	 * @param elementID html要素のID（出勤ボタンor退勤ボタン）
	 * @return 処理結果(Boolean)を含むマップ
	 */
    @RequestMapping(value = "/attendance", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSelectData(@RequestBody String elementID) {
    	// 返り値準備
    	Map<String, Object> resultMap = new ConcurrentHashMap<>();
        try {
			// サービスメソッド実行、返り値受け取り。
			Map<String, Object> recieveOnlyMap = infoService.pushAttButton(elementID, session);
			// 登録結果を返却地に含める。
			resultMap.put("registResult", (Boolean)recieveOnlyMap.get("attResult"));
			// セッションに登録後のレコードIDを設定する。
			session.setTWorkUnitHisId((Integer)recieveOnlyMap.get("latestRecordId"));
			return resultMap;
        } catch (CustomServiceException e) {
			// 登録処理に失敗。
			e.printStackTrace();
			resultMap.put("registResult", false);
			return resultMap;
		}
    }


}
