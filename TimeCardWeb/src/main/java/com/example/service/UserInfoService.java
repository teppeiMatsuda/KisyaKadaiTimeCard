package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.CustomServiceException;
import com.example.model.MTeam;
import com.example.model.MUser;
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
    public void registUser(MUser mUser, TUserDetail tUserDetail) throws CustomServiceException {
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
