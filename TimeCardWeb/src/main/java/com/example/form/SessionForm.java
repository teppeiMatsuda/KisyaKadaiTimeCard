package com.example.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * セッション情報持ち回り用クラス
 */
@Getter
@Setter
public class SessionForm implements Serializable {

	/**
	 * シリアライズ用
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 現在ログイン中ユーザーのID
	 */
	Integer UserId;

	/**
	 * 出退勤紐づけテーブルレコードのID
	 */
	Integer tWorkUnitHisId;

	/**
	 * セッションフォーム内容確認用メソッド
	 * @return セッションフォーム保持データ内容文字列
	 */
	@Override
	public String toString() {
		StringBuilder stringB = new StringBuilder();
		stringB.append("ユーザーID : ").append(this.UserId).append(" | ")
		.append("出退勤紐づけレコードID : ").append(this.tWorkUnitHisId);
		return stringB.toString();
	}

}
