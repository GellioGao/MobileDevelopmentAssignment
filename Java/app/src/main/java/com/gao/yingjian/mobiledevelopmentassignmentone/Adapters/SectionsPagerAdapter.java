package com.gao.yingjian.mobiledevelopmentassignmentone.Adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetail;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.SurveyDetailGroup;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.gao.yingjian.mobiledevelopmentassignmentone.Views.SurveyDetailFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    private SurveyDetail detail;

    public SectionsPagerAdapter(Context context, SurveyDetail detail, FragmentManager fm) {
        super(fm);
        mContext = context;
        this.detail = detail;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a SurveyDetailFragment (defined as a static inner class below).
        SurveyDetailGroup group = detail.getSurveyDetailGroups().get(position);
        SurveyDetailFragment fragment = SurveyDetailFragment.newInstance(group.getGroupName());
        fragment.setSurveyDetailGroup(detail.getDetailInfo().getSurveyId(), group);
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return detail.getSurveyDetailGroups().get(position).getGroupName();
    }

    @Override
    public int getCount() {
        return detail.getSurveyDetailGroups().size();
    }
}