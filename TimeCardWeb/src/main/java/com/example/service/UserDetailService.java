package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.TUserDetail;
import com.example.model.TUserDetailExample;
import com.example.repository.TUserDetailMapper;

@Transactional
@Service
public class UserDetailService {

	@Autowired
	TUserDetailMapper mapper;

	@Transactional(readOnly = true)
	public TUserDetail findByUserId(Integer userId) {
		TUserDetail entity = new TUserDetail();
		TUserDetailExample example = new TUserDetailExample();
		example.createCriteria().andUserIdEqualTo(userId);
		entity = mapper.selectByExample(example).get(0);
		return entity;
	}
}
