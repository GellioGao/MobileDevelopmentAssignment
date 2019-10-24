package com.gao.yingjian.mobiledevelopmentassignmentone.Models;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class SurveyDetailInfo {
    private String surveyId;
    private String surveyName;
    private String surveyDescription;
    private Date dueDate;
    private String surveyData;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getSurveyDescription() {
        return surveyDescription;
    }

    public void setSurveyDescription(String surveyDescription) {
        this.surveyDescription = surveyDescription;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getSurveyData() {
        return surveyData;
    }

    public void setSurveyData(String surveyData) {
        this.surveyData = surveyData;
    }
}
