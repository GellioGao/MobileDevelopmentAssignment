package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import androidx.lifecycle.ViewModel;

import com.gao.yingjian.mobiledevelopmentassignmentone.Models.UserEntity;

public class HomeViewModel extends ViewModel {
    private UserEntity user;
    private int newSurveyCount;

    public int getNewSurveyCount() {
        return newSurveyCount;
    }

    public HomeViewModel() {
        super();
        newSurveyCount = 10;
    }

    public HomeViewModel(UserEntity user){
        super();
        this.user = user;
    }
}
