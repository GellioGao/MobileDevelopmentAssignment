package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.HomeGridViewAdapter;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels.HomeViewModel;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private HomeViewModel mViewModel;

    private GridView gridView;
    private TextView txvUserName;

    private String username;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        gridView = view.findViewById(R.id.gdvTask);
        txvUserName = view.findViewById(R.id.txvWelcomeUserName);
        username = getActivity().getIntent().getStringExtra("user");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
        LayoutInflater inflater = getLayoutInflater();
        gridView.setAdapter(new HomeGridViewAdapter(inflater, getContext(), mViewModel.getNewSurveyCount()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HomeNavigationDrawerActivity home = (HomeNavigationDrawerActivity)getActivity();
                HashMap<String, String> map = new HashMap<>();
                map.put("user", username);
                Map.Entry<String, String>[] entries = new Map.Entry[map.size()];
                map.entrySet().toArray(entries);
                home.jumpTo(SurveyListActivity.class, entries);
            }
        });

        Intent intent = getActivity().getIntent();
        username = intent.getStringExtra("user");
        txvUserName.setText(username);
    }

}
