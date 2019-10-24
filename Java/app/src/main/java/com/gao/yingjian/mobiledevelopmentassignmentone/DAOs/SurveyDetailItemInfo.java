package com.gao.yingjian.mobiledevelopmentassignmentone.DAOs;

import org.litepal.crud.LitePalSupport;

public class SurveyDetailItemInfo extends LitePalSupport {
    private String surveyId;
    private int itemId;
    private String value;

    public SurveyDetailItemInfo() {
    }

    public SurveyDetailItemInfo(String surveyId, int itemId, String value) {
        this.surveyId = surveyId;
        this.itemId = itemId;
        this.value = value;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
