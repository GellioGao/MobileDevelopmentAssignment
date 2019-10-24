package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import android.content.Intent;
import android.os.Bundle;

import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetail;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailGroup;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.Services.FileManager;
import com.gao.yingjian.mobiledevelopmentassignmentone.Services.SurveyDataManager;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.SectionsPagerAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SurveyDetailActivity extends ActivityBase {
    public static final String INTENT_EXTRA_SURVEY_ID ="SurveyId";
    public static final int REQUEST_CODE_SUBMIT = 1;
    public static final int RESULT_CODE_SUBMITTED = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_detail);

        TextView title = findViewById(R.id.title);
        Intent intent = getIntent();
        String surveyId = intent.getStringExtra(INTENT_EXTRA_SURVEY_ID);
        SurveyDetailInfo detailInfo = SurveyDataManager.getInstance().getSurveyDetailInfo(this, surveyId);
        title.setText(detailInfo.getSurveyName());

        SurveyDetail detail = getSurveyDetail(detailInfo);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, detail, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SUBMIT && resultCode == RESULT_CODE_SUBMITTED){
            finish();
        }
    }

    public void doneSurvey(View view) {
        HashMap<String, String> map = new HashMap<>();
        String surveyId = getIntent().getStringExtra(INTENT_EXTRA_SURVEY_ID);
        map.put(INTENT_EXTRA_SURVEY_ID, surveyId);
        Map.Entry<String, String>[] entries = new Map.Entry[map.size()];
        map.entrySet().toArray(entries);
        super.jumpTo(SubmissionActivity.class, REQUEST_CODE_SUBMIT);
    }

    private SurveyDetail getSurveyDetail(SurveyDetailInfo detailInfo) {
        SurveyDetail detail = new SurveyDetail(detailInfo,SurveyDataManager.getInstance().getSurveyDetailGroups(this, detailInfo.getSurveyId()));
        return detail;
    }
}