package com.gao.yingjian.mobiledevelopmentassignmentone.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.SurveyDetailInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;

import java.util.ArrayList;

public class SurveyListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<SurveyDetailInfo> surveyInfos;

    public SurveyListAdapter(LayoutInflater layoutInflater, ArrayList<SurveyDetailInfo> surveyInfos) {
        this.layoutInflater = layoutInflater;
        this.surveyInfos = surveyInfos;
    }

    @Override
    public int getCount() {
        return surveyInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return surveyInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View item = this.layoutInflater.inflate(R.layout.list_view_title_date_item, null);
        TextView title = item.findViewById(R.id.listViewTitleDateItemTitle);
        TextView description = item.findViewById(R.id.listViewTitleDateItemDescription);
        TextView date = item.findViewById(R.id.listViewTitleDateItemDate);
        SurveyDetailInfo info = this.surveyInfos.get(i);
        title.setText(info.getSurveyName());
        description.setText(info.getSurveyDescription());
        date.setText(info.getDueDate().toString());

        return item;
    }
}
