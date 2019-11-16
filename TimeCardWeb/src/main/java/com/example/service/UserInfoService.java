package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.CustomServiceException;
import com.example.form.UserInfoForm;
import com.example.model.MTeam;
import com.example.model.MUser;
import com.example.model.MUserExample;
import com.example.model.TUserDetail;
import com.example.repository.MTeamMapper;
import com.example.repository.MUserMapper;
import com.example.repository.TUserDetailMapper;

@Service
public class UserInfoService {

    @Autowired
    MUserMapper mUserMapper;

    @Autowired
    TUserDetailMapper tUserDetailMapper;

    @Autowired
    MTeamMapper mTeamMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

    public MUser getUser(int userId) {
        return mUserMapper.selectByUserId(userId);
    }

    public MUser getUser(String loginId) {
        return mUserMapper.selectByLoginId(loginId);
    }

    public TUserDetail getUserDetail(int userId) {
        return tUserDetailMapper.selectByUserId(userId);
    }

    public List<MTeam> getTeamList() {
        return mTeamMapper.selectAll();
    }

    @Transactional
    public void registUser(UserInfoForm userInfoForm) throws CustomServiceException {

        MUser mUser = new MUser();
        MUserExample example = new MUserExample ();
//    	mUser.setUserId((int)mUserMapper.countByExample(example) + 1);
        mUser.setLoginId(userInfoForm.getLoginId());
        mUser.setPassword(passwordEncoder.encode(userInfoForm.getPassword()));
        mUser.setUserName(userInfoForm.getUserName());
        mUser.setRoleId("2");
        mUser.setDeleteFlag("0");

        TUserDetail tUserDetail = new TUserDetail();
        tUserDetail.setUserId((int)mUserMapper.countByExample(example)+1);

        tUserDetail.setStartOfWorkTime(this.timeFormat(userInfoForm.getStartOfWorkTime()));
        tUserDetail.setEndOfWorkTime(this.timeFormat(userInfoForm.getEndOfWorkTime()));

        tUserDetail.setTeamId(1);
        tUserDetail.setDeleteFlag("0");

        int mUserResultNum = mUserMapper.insert(mUser);
        int tUserDetailResultNum = tUserDetailMapper.insert(tUserDetail);

        if(mUserResultNum == 0 && tUserDetailResultNum == 0) {
            throw new CustomServiceException("ユーザー情報の登録に失敗しました。");
        }
    }

    public String timeFormat(String time) {
//    	String [] str = time.split(":");
    	if(time.contains(":")) {
    		time += ":00";
    	}else {
    		time += ":00:00";
    	}
    	return time;
    }

    @Transactional
    public void updateUser(MUser mUser, TUserDetail tUserDetail)  throws CustomServiceException{
        int mUserResultNum = mUserMapper.updateByUserId(mUser);
        int tUserDetailResultNum = tUserDetailMapper.updateByUserId(tUserDetail);

        if(mUserResultNum == 0 || tUserDetailResultNum == 0) {
            throw new CustomServiceException("ユーザー情報の登録に失敗しました。");
        }
    }

}
