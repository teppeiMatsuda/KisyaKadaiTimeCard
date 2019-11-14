package com.example.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserInfoForm {
    String loginId;
    String userName;
    String password;
    String startOfWorkTime;
    String endOfWorkTime;
    int teamId;
}
