package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.RecentSurvey;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.Services.SurveyDataManager;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;

public class SurveyListViewModel extends ViewModel {
    private ArrayList<SurveyDetailInfo> surveyInfos;

    public SurveyListViewModel(){
        super();
    }

    public ArrayList<SurveyDetailInfo> getSurveyList(Context context) {
        if(surveyInfos == null){
            surveyInfos = SurveyDataManager.getInstance().getSurveyDetailList(context);
        }
        return surveyInfos;
    }

    public SurveyDetailInfo selectedSurvey(int index, String username) {
        SurveyDetailInfo survey = surveyInfos.get(index);
        RecentSurvey recentSurvey = LitePal
                .where("surveyId = ?", survey.getSurveyId())
                .findFirst(RecentSurvey.class);
        if(recentSurvey == null ) {
            recentSurvey = new RecentSurvey();
            recentSurvey.setSurveyId(survey.getSurveyId());
            recentSurvey.setSurveyName(survey.getSurveyName());
            recentSurvey.setSurveyDescription(survey.getSurveyDescription());
            recentSurvey.setUsername(username);
        }
        recentSurvey.setLastModified(new Date());
        recentSurvey.save();

        return survey;
    }
}
