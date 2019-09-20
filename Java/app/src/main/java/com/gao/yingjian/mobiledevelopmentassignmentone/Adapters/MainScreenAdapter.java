package com.gao.yingjian.mobiledevelopmentassignmentone.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.gao.yingjian.mobiledevelopmentassignmentone.Models.MainScreen;

import java.util.ArrayList;

public class MainScreenAdapter extends FragmentStatePagerAdapter {
    private ArrayList<MainScreen> screens;

    public MainScreenAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.screens = new ArrayList<>();
    }

    public void setItems(ArrayList<MainScreen> screens) {
        this.screens.clear();
        this.screens.addAll(screens);
        notifyDataSetChanged();
    }

    public ArrayList<MainScreen> getItems(){
        return this.screens;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.screens.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return this.screens.size();
    }
}
