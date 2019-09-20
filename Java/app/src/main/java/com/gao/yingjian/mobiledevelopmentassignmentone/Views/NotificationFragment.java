package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.NotificationListAdapter;
import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.RecentListAdapter;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels.NotificationViewModel;

public class NotificationFragment extends Fragment {
    private static final String TAG = "NotificationFragment";

    private NotificationViewModel mViewModel;

    private ListView ltvNotification;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.notification_fragment, container, false);
        ltvNotification = view1.findViewById(R.id.ltvNotification);
        return view1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        // TODO: Use the ViewModel
        ltvNotification.setAdapter(new NotificationListAdapter(getLayoutInflater(), mViewModel.getNotificationList()));
    }
}
