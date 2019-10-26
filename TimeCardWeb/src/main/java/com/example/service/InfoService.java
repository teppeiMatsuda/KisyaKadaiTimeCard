package com.example.service;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.Const;
import com.example.exception.CustomServiceException;
import com.example.model.MUser;
import com.example.model.TWorkEndHis;
import com.example.model.TWorkStartHis;
import com.example.model.TWorkUnitHis;
import com.example.repository.MUserMapper;
import com.example.repository.TWorkEndHisMapper;
import com.example.repository.TWorkStartHisMapper;
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
	 * 出勤履歴テーブルMapper
	 */
	@Autowired
	TWorkStartHisMapper tWorkStartHisMapper;

	/**
	 * 退勤履歴テーブルMapper
	 */
	@Autowired
	TWorkEndHisMapper tWorkEndHisMapper;

	public Map<String, Object> initialDisplay(Principal principal) throws CustomServiceException {
		//返り値準備
		HashMap<String, Object> userStateMap = new HashMap<>();
		// 勤怠情報を取得するために必要なユーザー情報(ユーザーID)を取得する。
		MUser user = mUserMapper.selectByLoginId(principal.getName());

		if (user == null) {
			throw new CustomServiceException("登録されているユーザー情報に異常がありました。");
		}

		// 出退勤紐づけテーブルからユーザーIDと本日の日付(curdate())を元に
		// 出勤情報のデータが存在するかチェック。
		List<TWorkUnitHis> tWorkUnitHisList = tWorkUnitMapper.selectByUserIdAndCurrentDate(user.getUserId());

		if (tWorkUnitHisList.size() >= 1) {

			// 出退勤紐づけテーブルにレコードが存在した場合、
			// 返り値に出勤フラグをセット(true)、2以上は考慮しない。
			userStateMap.put("wStartFlg", true);
			TWorkUnitHis todayAttRecord = tWorkUnitHisList.get(0);
			TWorkEndHis todayWorkEndRecord = tWorkEndHisMapper.selectByWorkUnitHisId(todayAttRecord.getWorkUnitHisId());

			if (todayWorkEndRecord != null) {
				userStateMap.put("wEndFlg", true);
			} else {
				userStateMap.put("wEndFlg", false);
			}

		} else {
			// 出退勤紐づけテーブルにレコードが存在しない場合、出勤・退勤フラグはfalse
			userStateMap.put("wStartFlg", false);
			userStateMap.put("wEndFlg", false);
		}

		// 返り値に取得したユーザーIDをセットする。
		userStateMap.put("userId", user.getUserId());
		// principalインターフェースを実装しているクラスから取得できるため、
		// 本来であれば必要ないがこのタイミングで権限IDを取得する。
		userStateMap.put("roleId", user.getRoleId());
		return userStateMap;
	}

	@Transactional
	public Boolean pushAttButton(String elementID, Integer userId) throws CustomServiceException {
		if(!"".equals(elementID)) {
			if(elementID.equals(Const.ATTENDANCE)) {
				TWorkUnitHis workUnitRec = new TWorkUnitHis();
				// ユーザーIDをセット
				workUnitRec.setUserId(userId);
				// 現在の日付をセット
				workUnitRec.setWorkDate(new Date());
				// インサート実行
				int resultTWorkRowNum = tWorkUnitMapper.insertSelective(workUnitRec);

				if(resultTWorkRowNum == 0) {
					throw new CustomServiceException("出勤記録の登録に失敗しました。");
				}
				TWorkUnitHis latestRecord = tWorkUnitMapper.selectLatestRecord();

				TWorkStartHis tWorkStartRec = new TWorkStartHis();
				// 登録済みの紐づけテーブルのIDをセット
				tWorkStartRec.setWorkUnitHisId(latestRecord.getWorkUnitHisId());
				// 現在日時をセット
				tWorkStartRec.setStartOfWorkTime(new Date());
				// インサート実行。
				int resultNum = tWorkStartHisMapper.insert(tWorkStartRec);

				if(resultNum == 0) {
					throw new CustomServiceException("出勤記録の登録に失敗しました。");
				}

			}else if(elementID.equals(Const.LEAVE)) {


			}
			return true;
		}
		throw new CustomServiceException("クライアントから不正な値が送信されました。");
	}

}
