package com.gao.yingjian.mobiledevelopmentassignmentone.Services;

import android.content.Context;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.SurveyDetailItemInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetail;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailGroup;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailInfo;
import com.google.gson.reflect.TypeToken;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class SurveyDataManager {
    private static final SurveyDataManager ourInstance = new SurveyDataManager();

    public static SurveyDataManager getInstance() {
        return ourInstance;
    }

    private ArrayList<SurveyDetailInfo> infoList;

    private SurveyDataManager() {
    }

    public void updateSurveyDetailItemData(String surveyId, int itemId, String value){
        SurveyDetailItemInfo info = LitePal
                .where("surveyId = ? and itemId = ?", surveyId, String.valueOf(itemId))
                .findFirst(SurveyDetailItemInfo.class);
        if(info == null) {
            info = new SurveyDetailItemInfo(surveyId, itemId, value);
        }
        info.setValue(value);
        info.save();
    }

    public ArrayList<SurveyDetailItemInfo> getSurveyDetailItemData(String surveyId){
            List<SurveyDetailItemInfo> data = LitePal
                .where("surveyId = ?", surveyId)
                .find(SurveyDetailItemInfo.class);
            ArrayList<SurveyDetailItemInfo> result = new ArrayList<>();
            for(SurveyDetailItemInfo info : data){
                result.add(info);
            }
        return result;
    }

    public ArrayList<SurveyDetailInfo> getSurveyDetailList(Context context){
        if(this.infoList == null) {
            this.infoList = FileManager.getInstance()
                    .getObjectFromJson("sample_survey_list.json", context, new TypeToken<ArrayList<SurveyDetailInfo>>() {
                    }.getType());
        }
        return this.infoList;
    }

    public SurveyDetailInfo getSurveyDetailInfo(Context context, String surveyId) {
        if(this.infoList == null){
            this.getSurveyDetailList(context);
        }
        for(SurveyDetailInfo info : this.infoList){
            if(info.getSurveyId().equals(surveyId)){
                return info;
            }
        }
        return null;
    }

    public ArrayList<SurveyDetailGroup> getSurveyDetailGroups(Context context, String surveyId) {
        SurveyDetail detail = FileManager.getInstance().getObjectFromJson("sample_survey_detail.json", context, SurveyDetail.class);
        if(detail == null){
            return new ArrayList<>();
        }
        return detail.getSurveyDetailGroups();
    }
}
