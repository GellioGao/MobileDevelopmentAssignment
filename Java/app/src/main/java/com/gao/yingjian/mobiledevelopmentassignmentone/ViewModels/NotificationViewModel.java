package com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels;

import androidx.lifecycle.ViewModel;

import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.NotificationInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.RecentSurvey;

import org.litepal.LitePal;

import java.util.ArrayList;

public class NotificationViewModel extends ViewModel {
    public ArrayList<NotificationInfo> getNotificationList() {
        ArrayList<NotificationInfo> notificationInfos = new ArrayList<>();
        for(NotificationInfo info : LitePal.findAll(NotificationInfo.class)){
            notificationInfos.add(info);
        }
        return notificationInfos;
    }
}
