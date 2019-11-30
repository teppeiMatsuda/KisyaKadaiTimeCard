package com.example.form;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
	 * セッションフォームBeanが保持する情報をすべて削除するメソッドです。
	 */
	public void clear() {
		this.UserId = null;
		this.tWorkUnitHisId = null;
	}	

	/**
	 * セッションフォーム内容確認用メソッド
	 * @return セッションフォーム保持データ内容文字列
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
		// ↓ ダサいのでコメントアウトします…。
		// StringBuilder stringB = new StringBuilder();
		// stringB.append("ユーザーID : ").append(this.UserId).append(" | ")
		// .append("出退勤紐づけレコードID : ").append(this.tWorkUnitHisId);
		// return stringB.toString();
	}

}
