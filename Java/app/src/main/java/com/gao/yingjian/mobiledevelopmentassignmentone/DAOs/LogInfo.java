package com.gao.yingjian.mobiledevelopmentassignmentone.DAOs;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class LogInfo extends LitePalSupport {
    private String userName;
    private int loginCount;
    private Date lastLoginTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void loginSucceed(){
        loginCount++;
        lastLoginTime = new Date();
    }
}
