package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailGroup;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailItem;

import java.util.ArrayList;

public class SurveyDetailViewModel extends ViewModel {

    private MutableLiveData<String> mTitle = new MutableLiveData<>();

    private LiveData<String> mText = Transformations.map(mTitle, new Function<String, String>() {
        @Override
        public String apply(String input) {
            return "Hello world from section: " + input;
        }
    });
    private String surveyId;
    private SurveyDetailGroup surveyDetailGroup;

    public ArrayList<SurveyDetailItem> getSurveyDetailItems() {
        return surveyDetailGroup.getItems();
    }

    public void setTitle(String title) {
        mTitle.setValue(title);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setSurveyDetailGroup(String surveyId, SurveyDetailGroup group) {
        this.surveyId = surveyId;
        this.surveyDetailGroup = group;
    }

    public String getSurveyId() {
        return this.surveyId;
    }
}