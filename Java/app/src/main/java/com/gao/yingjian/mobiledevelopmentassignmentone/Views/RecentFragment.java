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

import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.RecentListAdapter;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.ViewModels.RecentViewModel;

public class RecentFragment extends Fragment {
    private static final String TAG = "RecentFragment";

    private RecentViewModel mViewModel;

    private ListView ltvRecent;

    private String username;

    public static RecentFragment newInstance() {
        return new RecentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view1= inflater.inflate(R.layout.recent_fragment, container, false);
        ltvRecent = view1.findViewById(R.id.ltvRecent);
        username = getActivity().getIntent().getStringExtra("user");
        return view1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecentViewModel.class);
        // TODO: Use the ViewModel
        ltvRecent.setAdapter(new RecentListAdapter(getLayoutInflater(), mViewModel.getRecentList(username)));
    }

}
