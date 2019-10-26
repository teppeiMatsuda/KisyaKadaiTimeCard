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
	 * 現在ログイン中ユーザーのID
	 */
	Integer UserId;

}
