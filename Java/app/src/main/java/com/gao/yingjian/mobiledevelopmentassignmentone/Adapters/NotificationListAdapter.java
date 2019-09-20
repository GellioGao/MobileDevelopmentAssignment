package com.gao.yingjian.mobiledevelopmentassignmentone.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.NotificationInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;

import java.util.ArrayList;

public class NotificationListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<NotificationInfo> notificationInfos;

    public NotificationListAdapter(LayoutInflater layoutInflater, ArrayList<NotificationInfo> notificationInfos) {
        this.layoutInflater = layoutInflater;
        this.notificationInfos = notificationInfos;
    }

    @Override
    public int getCount() {
        return notificationInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return notificationInfos.get(i);
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
        NotificationInfo info = this.notificationInfos.get(i);
        title.setText(info.getTitle());
        description.setText(info.getNotification());
        date.setText(info.getNotificationDate().toString());

        return item;
    }
}
