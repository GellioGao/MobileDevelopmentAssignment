package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import com.gao.yingjian.mobiledevelopmentassignmentone.Models.UserEntity;

public final class ViewModelFactory {
    public static final LoginViewModel getLoginViewModel() {
        return new LoginViewModel();
    }

    public static final HomeViewModel getHomeViewModel() {
        UserEntity user = new UserEntity(1,"GYJ","Yingjian", "Gao");
        return new HomeViewModel(user);
    }
}
