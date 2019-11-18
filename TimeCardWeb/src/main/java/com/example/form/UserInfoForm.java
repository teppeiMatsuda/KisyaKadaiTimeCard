package com.example.form;

import java.util.List;

import com.example.model.MUser;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserInfoForm {
    String userId;
    String loginId;
    String userName;
    String password;
    String startOfWorkTime;
    String endOfWorkTime;
    String teamId;
    String roleId;
    List<MUser> userlist;
}
