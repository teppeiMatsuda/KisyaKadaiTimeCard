package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.exception.CustomServiceException;
import com.example.form.UserInfoForm;
import com.example.model.MRole;
import com.example.model.MTeam;
import com.example.model.MUser;
import com.example.model.MUserExample;
import com.example.model.TUserDetail;
import com.example.repository.MRoleMapper;
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
    MRoleMapper mRoleMapper;

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
    public List<MRole> getRoleList() {
        return mRoleMapper.selectAll();
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

        tUserDetail.setStartOfWorkTime(this.startTimeFormat(userInfoForm.getStartOfWorkTime()));
        tUserDetail.setEndOfWorkTime(this.endTimeFormat(userInfoForm.getEndOfWorkTime()));

        tUserDetail.setTeamId(Integer.parseInt(userInfoForm.getTeamId()));
        tUserDetail.setDeleteFlag("0");

        int mUserResultNum = mUserMapper.insert(mUser);
        int tUserDetailResultNum = tUserDetailMapper.insert(tUserDetail);

        if(mUserResultNum == 0 && tUserDetailResultNum == 0) {
            throw new CustomServiceException("ユーザー情報の登録に失敗しました。");
        }
    }

    public String startTimeFormat(String time) {
    	if(StringUtils.isEmpty(time)) {
    		time = "09:00:00";
    	}
    	else if(time.contains(":")) {
    		time += ":00";
    	}else {
    		time += ":00:00";
    	}
    	return time;
    }

    public String endTimeFormat(String time) {
    	if(StringUtils.isEmpty(time)) {
    		time = "17:00:00";
    	}
    	else if(time.contains(":")) {
    		time += ":00";
    	}else {
    		time += ":00:00";
    	}
    	return time;
    }

    @Transactional
    public void updateUser(UserInfoForm userInfoForm)  throws CustomServiceException{

        MUser mUser = new MUser();
        TUserDetail tUserDetail = new TUserDetail();
        mUser  = this.mUserCheck(userInfoForm);
        tUserDetail  = this.tUserDetailCheck(userInfoForm);
	int mUserResultNum = mUserMapper.updateByPrimaryKeySelective(mUser);
	int tUserDetailResultNum = tUserDetailMapper.updateByPrimaryKey(tUserDetail);


        if(mUserResultNum == 0 || tUserDetailResultNum == 0) {
            throw new CustomServiceException("ユーザー情報の登録に失敗しました。");
        }
    }

    public MUser mUserCheck(UserInfoForm userInfoForm) {
    	MUser mUser = new MUser();
//    	updateするユーザーの取得
    	mUser = this.getUser(Integer.parseInt(userInfoForm.getUserId()));

    	if(!(StringUtils.isEmpty(userInfoForm.getLoginId()))) {
    		mUser.setLoginId(userInfoForm.getLoginId());
    	}
    	if(!(StringUtils.isEmpty(userInfoForm.getPassword()))) {
    		mUser.setPassword(passwordEncoder.encode(userInfoForm.getPassword()));
    	}
    	if(!(StringUtils.isEmpty(userInfoForm.getUserName()))) {
    		mUser.setUserName(userInfoForm.getUserName());
    	}
    	if(!(StringUtils.isEmpty(userInfoForm.getRoleId()))) {
    		mUser.setRoleId(userInfoForm.getRoleId());
    	}
        mUser.setDeleteFlag("0");

    	return mUser;
    }

    public TUserDetail tUserDetailCheck(UserInfoForm userInfoForm) {
    	TUserDetail tUserDetail = new TUserDetail();
//    	updateするユーザー詳細情報の取得
    	tUserDetail = this.getUserDetail(Integer.parseInt(userInfoForm.getUserId()));

    	if(!(StringUtils.isEmpty(userInfoForm.getStartOfWorkTime()))) {
    		tUserDetail.setStartOfWorkTime(this.startTimeFormat(userInfoForm.getStartOfWorkTime()));
    	}
    	if(!(StringUtils.isEmpty(userInfoForm.getEndOfWorkTime()))) {
    		tUserDetail.setEndOfWorkTime(this.endTimeFormat(userInfoForm.getEndOfWorkTime()));
    	}
    	if(!(StringUtils.isEmpty(userInfoForm.getTeamId()))) {
    		tUserDetail.setTeamId(Integer.parseInt(userInfoForm.getTeamId()));
    	}
        tUserDetail.setDeleteFlag("0");

    	return tUserDetail;
    }

    @Transactional
    public void deleteUser(UserInfoForm userInfoForm)  throws CustomServiceException{
    	MUser mUser = new MUser();
    	TUserDetail tUserDetail = new TUserDetail();
//    	updateするユーザーの取得
    	mUser = this.getUser(Integer.parseInt(userInfoForm.getUserId()));
    	tUserDetail = this.getUserDetail(Integer.parseInt(userInfoForm.getUserId()));

    	mUser.setDeleteFlag("1");
    	tUserDetail.setDeleteFlag("1");

    	int mUserResultNum = mUserMapper.deleteUser(mUser);
        int tUserDetailResultNum = tUserDetailMapper.deleteUser(tUserDetail);

        if(mUserResultNum == 0 || tUserDetailResultNum == 0) {
            throw new CustomServiceException("ユーザー情報の登録に失敗しました。");
        }
    }


    /**
    *
    * @param userName
    * @return List<MUser>
    */
   public List<MUser> searchUser(String userName) {
	   
       MUserExample mUserExample = new MUserExample();
       mUserExample.createCriteria().andUserNameEqualTo(userName);
       List<MUser>mUserList = mUserMapper.selectByExample(mUserExample);

   return mUserList;
   }

}
