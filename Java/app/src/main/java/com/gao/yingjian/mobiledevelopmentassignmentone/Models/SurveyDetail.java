package com.gao.yingjian.mobiledevelopmentassignmentone.Models;

import java.util.ArrayList;

public class SurveyDetail {
    private SurveyDetailInfo detailInfo;
    private ArrayList<SurveyDetailGroup> surveyDetailGroups;

    public SurveyDetail(SurveyDetailInfo detailInfo, ArrayList<SurveyDetailGroup> surveyDetailGroups) {
        this.detailInfo = detailInfo;
        this.surveyDetailGroups = surveyDetailGroups;
    }

    public SurveyDetailInfo getDetailInfo() {
        return detailInfo;
    }

    public ArrayList<SurveyDetailGroup> getSurveyDetailGroups() {
        return surveyDetailGroups;
    }
}
