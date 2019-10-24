package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.SurveyDetailItemsAdapter;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailGroup;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels.SurveyDetailViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class SurveyDetailFragment extends Fragment {

    private static final String ARG_SECTION_TITLE = "section_title";

    private SurveyDetailViewModel surveyDetailViewModel;

    private ListView ltvSurveyDetailItems;

    private SurveyDetailGroup group;
    private String surveyId;

    public static SurveyDetailFragment newInstance(String title) {
        SurveyDetailFragment fragment = new SurveyDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SECTION_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surveyDetailViewModel = ViewModelProviders.of(this).get(SurveyDetailViewModel.class);
        String title = "";
        if (getArguments() != null) {
            title = getArguments().getString(ARG_SECTION_TITLE);
        }
        surveyDetailViewModel.setTitle(title);
        surveyDetailViewModel.setSurveyDetailGroup(this.surveyId, this.group);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey_detail, container, false);

        ltvSurveyDetailItems = root.findViewById(R.id.ltvSurveyDetailItems);
        ltvSurveyDetailItems.setAdapter(new SurveyDetailItemsAdapter(getLayoutInflater(), getContext(), surveyDetailViewModel.getSurveyId(), surveyDetailViewModel.getSurveyDetailItems()));

        surveyDetailViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    public void setSurveyDetailGroup(String surveyId, SurveyDetailGroup group){
        this.surveyId = surveyId;
        this.group = group;
    }
}