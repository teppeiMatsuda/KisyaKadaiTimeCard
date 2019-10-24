package com.example.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.CustomServiceException;
import com.example.model.MUser;
import com.example.model.TWorkUnitHis;
import com.example.repository.MUserMapper;
import com.example.repository.TWorkEndHisMapper;
import com.example.repository.TWorkUnitHisMapper;

/**
 * ログイン後に表示されるメインページのサービスクラスです。
 */
@Service
public class InfoService {

	/**
	 * 出退勤紐づけテーブルMapper
	 */
	@Autowired
	TWorkUnitHisMapper tWorkUnitMapper;

	/**
	 * ユーザーマスタMapper
	 */
	@Autowired
	MUserMapper mUserMapper;

	/**
	 * 退勤履歴テーブルMapper
	 */
	@Autowired
	TWorkEndHisMapper tWorkEndHisMapper;


	public Map<String,Object> initialDisplay(Principal principal) throws CustomServiceException {
		//返り値準備
		HashMap<String,Object> userStateMap = new HashMap<>();
		// 勤怠情報を取得するために必要なユーザー情報(ユーザーID)を取得する。
		MUser user = mUserMapper.selectByLoginId(principal.getName());
		if(user == null) {
			throw new CustomServiceException("登録されているユーザー情報に異常がありました。");
		}
		List<TWorkUnitHis> tWorkUnitHisList = tWorkUnitMapper.selectByUserIdAndCurrentDate(user.getUserId());

		if(tWorkUnitHisList.size() >= 1) {
			// 返り値に出勤フラグをセット(true)
			userStateMap.put("attFlg",true);
			tWorkUnitHisList.get(0);

		}

		// 返り値に取得したユーザーIDをセットする。
		userStateMap.put("currentUserId", user.getUserId());
		return userStateMap;
	}



}
