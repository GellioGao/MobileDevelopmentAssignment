package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import android.content.Intent;
import android.os.Bundle;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.SurveyDetailInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.SectionsPagerAdapter;

import org.litepal.LitePal;

public class SurveyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_detail);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        TextView title = findViewById(R.id.title);
        Intent intent = getIntent();
        String surveyId = intent.getStringExtra("SurveyId");
        SurveyDetailInfo detailInfo = LitePal
                .where("surveyId = ?", surveyId)
                .findFirst(SurveyDetailInfo.class);
        title.setText(detailInfo.getSurveyName());
    }
}