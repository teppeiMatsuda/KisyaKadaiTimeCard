package com.example.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.example.model.MRole;
import com.example.model.MUser;

public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {

    // Userエンティティ
    private MUser user;

    @Autowired
    private MRole mRole;

    // マッパー
    public MUser getUser() {
        return user;
    }

    public SimpleLoginUser(MUser user) {
        super(user.getLoginId(), user.getPassword(), determineRoles(user.getRoleId()));
        this.user = user;
    }

    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private static List<GrantedAuthority> determineRoles(String roleId) {
        if(roleId == null || roleId == "2") {
        	return USER_ROLES;
        }else {
        	return ADMIN_ROLES;
        }
    }
}
