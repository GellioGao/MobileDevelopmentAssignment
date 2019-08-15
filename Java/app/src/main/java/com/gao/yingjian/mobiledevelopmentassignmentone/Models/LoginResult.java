package com.gao.yingjian.mobiledevelopmentassignmentone.Models;

public class LoginResult {
    private boolean isLogin;

    private UserEntity user;

    public LoginResult(boolean isLogin, UserEntity user) {
        this.isLogin = isLogin;
        this.user = user;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public UserEntity getUser() {
        return user;
    }
}
