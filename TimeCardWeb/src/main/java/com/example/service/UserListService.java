package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.MUser;
import com.example.repository.MUserMapper;
/**
 * ユーザー情報 Service
 */
@Service
public class UserListService {

	/**
	 * * ユーザー情報 Repository
	 * */
	@Autowired
	private MUserMapper mUserMapper;

	/**
	 * * ユーザー情報 全検索
	 * * @return 検索結果
	 * */
	public List<MUser> searchAll() {
		return mUserMapper.findAll();
		}
}