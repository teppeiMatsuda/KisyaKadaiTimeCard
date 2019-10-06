package com.example.auth;

import static com.example.common.Const.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.MRole;
import com.example.model.MRoleKey;
import com.example.model.MUser;
import com.example.repository.MRoleMapper;
import com.example.repository.MUserMapper;

public class UserAccountService implements UserDetailsService {

	@Autowired
	private MUserMapper mUserMapper;

	@Autowired
	private MRoleMapper mRoleMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		if (loginId == null || "".equals(loginId)) {
			throw new UsernameNotFoundException("LoginID is empty");
		}

		// ログインID検索
		MUser userInfo = mUserMapper.selectByLoginId(loginId);

		if (userInfo == null) {
			throw new UsernameNotFoundException("User not found: " + loginId);
		}

		if (userInfo.getDeleteFlag() != null && userInfo.getDeleteFlag() == DELETE_FLG_ON) {
			throw new UsernameNotFoundException("User not found: " + loginId);
		}

		MRoleKey mRoleKey = new MRoleKey();
		mRoleKey.setRoleId(userInfo.getRoleId());
		MRole mRole = mRoleMapper.selectByPrimaryKey(mRoleKey);

		if (mRole == null || mRole.getRoleName() == null || mRole.getRoleName().equals("")) {
			throw new BadCredentialsException("Sorry, the user information is probably corrupted.");
		}

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(mRole.getRoleName());
		grantList.add(authority);

		// BCryptアルゴリズムで暗号化
		// UserDetailsインターフェースにUserオブジェクト代入
		User user = (User) new User(userInfo.getLoginId(), passwordEncoder.encode(userInfo.getPassword()), grantList);
		return user;
	}

	@Transactional
	public void registerUser(String loginId, String password, String userName, String roleId, String deleteFlag) {
		MUser user = new MUser();
		user.setLoginId(loginId);
		user.setPassword(password);
		user.setRoleId(roleId);
		user.setUserName(userName);
		user.setDeleteFlag(deleteFlag);
		mUserMapper.insert(user);
	}
}