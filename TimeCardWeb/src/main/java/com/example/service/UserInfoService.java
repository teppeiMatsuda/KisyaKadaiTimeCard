package com.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        mUser.setPassword(userInfoForm.getPassword());
        mUser.setUserName(userInfoForm.getUserName());
        mUser.setRoleId("2");

        TUserDetail tUserDetail = new TUserDetail();
        tUserDetail.setUserId((int)mUserMapper.countByExample(example) + 1);
        tUserDetail.setStartOfWorkTime(Date.valueOf(userInfoForm.getStartOfWorkTime()));
        tUserDetail.setEndOfWorkTime(Date.valueOf(userInfoForm.getEndOfWorkTime()));
        tUserDetail.setTeamId(1);
        int mUserResultNum = mUserMapper.insert(mUser);
        int tUserDetailResultNum = tUserDetailMapper.insert(tUserDetail);

        if(mUserResultNum == 0 || tUserDetailResultNum == 0) {
            throw new CustomServiceException("ユーザー情報の登録に失敗しました。");
        }
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
