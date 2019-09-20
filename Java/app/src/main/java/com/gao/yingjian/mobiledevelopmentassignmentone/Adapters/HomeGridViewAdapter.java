package com.gao.yingjian.mobiledevelopmentassignmentone.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.itingchunyu.badgeview.BadgeTextView;

public class HomeGridViewAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private int badgeCount;

    public HomeGridViewAdapter(LayoutInflater layoutInflater, Context cxt, int badgeCount) {
        super();
        this.layoutInflater = layoutInflater;
        this.context = cxt;
        this.badgeCount = badgeCount;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view1 = layoutInflater.inflate(R.layout.home_grid_view_task_item, null);
        TextView txvTask = view1.findViewById(R.id.txvHomeTask);

        BadgeTextView mBadgeView=new BadgeTextView(context);
        mBadgeView.setTargetView(txvTask);
        mBadgeView.setBadgeCount(badgeCount)
                .setmDefaultRightPadding(30)
                .setmDefaultTopPadding(30);
        return view1;
    }
}
