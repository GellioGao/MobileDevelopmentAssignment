package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import androidx.lifecycle.ViewModel;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.RecentSurvey;
import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.SurveyDetailInfo;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class SurveyListViewModel extends ViewModel {
    private ArrayList<SurveyDetailInfo> surveyInfos;

    public SurveyListViewModel(){
        super();
        surveyInfos = getTestData();
    }

    public ArrayList<SurveyDetailInfo> getSurveyList() {
        return surveyInfos;
    }

    private ArrayList<SurveyDetailInfo> getTestData() {
        ArrayList<SurveyDetailInfo> results = new ArrayList<>();
        for (SurveyDetailInfo detailInfo : LitePal.findAll(SurveyDetailInfo.class)) {
            results.add(detailInfo);
        }
        if (results.size() > 0) {
            return results;
        }

        Date now = new Date();
        for (int i = 0; i < 20; i++) {
            for (int a = 0; a < 10; a++) {
                SurveyDetailInfo surveyInfo = new SurveyDetailInfo();
                surveyInfo.setSurveyName("Survey " + (i * 10 + a));
                surveyInfo.setSurveyDescription("Survey description " + (i * 10 + a) + ".");
                surveyInfo.setDueDate(now);
                surveyInfo.setSurveyId(UUID.randomUUID().toString());
                surveyInfo.save();
                results.add(surveyInfo);
            }
        }
        return results;
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
