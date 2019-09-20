package com.gao.yingjian.mobiledevelopmentassignmentone.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.RecentSurvey;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;

import java.util.ArrayList;

public class RecentListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<RecentSurvey> recentSurveys;

    public RecentListAdapter(LayoutInflater layoutInflater, ArrayList<RecentSurvey> recentSurveys) {
        this.layoutInflater = layoutInflater;
        this.recentSurveys = recentSurveys;
    }

    @Override
    public int getCount() {
        return recentSurveys.size();
    }

    @Override
    public Object getItem(int i) {
        return recentSurveys.get(i);
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
        RecentSurvey recent = this.recentSurveys.get(i);
        title.setText(recent.getSurveyName());
        description.setText(recent.getSurveyDescription());
        date.setText(recent.getLastModified().toString());

        return item;
    }
}
