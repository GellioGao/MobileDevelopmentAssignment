package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import androidx.lifecycle.ViewModel;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.RecentSurvey;

import org.litepal.LitePal;

import java.util.ArrayList;

public class RecentViewModel extends ViewModel {
    public ArrayList<RecentSurvey> getRecentList(String username) {
        ArrayList<RecentSurvey> recentSurveys = new ArrayList<>();
        for(RecentSurvey recent : LitePal.where("username = ?", username).find(RecentSurvey.class)){
            recentSurveys.add(recent);
        }
        return recentSurveys;
    }
}
