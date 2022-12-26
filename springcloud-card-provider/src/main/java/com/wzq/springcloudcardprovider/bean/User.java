package com.wzq.springcloudcardprovider.bean;

import com.wzq.springcloudcardprovider.annatation.Check;
import com.wzq.springcloudcardprovider.annatation.CheckValidatorEnum;

public class User {
    @Check(type = CheckValidatorEnum.Null,message = "姓名不能为空")
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
