package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.SurveyListAdapter;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels.SurveyListViewModel;

import java.util.HashMap;
import java.util.Map;

public class SurveyListActivity extends ActivityBase {
    private static final String TAG = "SurveyListActivity";

    private SurveyListViewModel mViewModel;

    private ListView ltvSurveyList;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);

        mViewModel = ViewModelProviders.of(this).get(SurveyListViewModel.class);

        username = getIntent().getStringExtra(HomeNavigationDrawerActivity.INTENT_EXTRA_USER);
        ltvSurveyList = findViewById(R.id.ltvSurveyList);
        ltvSurveyList.setAdapter(new SurveyListAdapter(getLayoutInflater(), mViewModel.getSurveyList(this)));
        ltvSurveyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SurveyDetailInfo surveyInfo = mViewModel.selectedSurvey(i, username);
                HashMap<String, String> map = new HashMap<>();
                map.put(SurveyDetailActivity.INTENT_EXTRA_SURVEY_ID, surveyInfo.getSurveyId());
                Map.Entry<String, String>[] entries = new Map.Entry[map.size()];
                map.entrySet().toArray(entries);
                jumpTo(SurveyDetailActivity.class, entries);
            }
        });
    }
}
