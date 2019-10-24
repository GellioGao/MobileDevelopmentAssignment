package com.gao.yingjian.mobiledevelopmentassignmentone.Models;

import java.util.ArrayList;

public class SurveyDetailGroup {
    private String groupName;
    private String groupDescription;
    private ArrayList<SurveyDetailItem> items;

    public SurveyDetailGroup(String groupName, String groupDescription, ArrayList<SurveyDetailItem> items) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.items = items;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public ArrayList<SurveyDetailItem> getItems() {
        return items;
    }
}
