package com.gao.yingjian.mobiledevelopmentassignmentone.Models;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public class MainScreen {
    private @IdRes int menuItemId;
    private @DrawableRes int menuItemIconId;
    private @StringRes int titleStringId;
    private Fragment fragment;

    public int getMenuItemId() {
        return menuItemId;
    }

    public int getMenuItemIconId() {
        return menuItemIconId;
    }

    public int getTitleStringId() {
        return titleStringId;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public MainScreen(@IdRes int menuItemId, @DrawableRes int menuItemIconId, @StringRes int titleStringId, Fragment fragment){
        this.menuItemId = menuItemId;
        this.menuItemIconId=menuItemIconId;
        this.titleStringId=titleStringId;
        this.fragment=fragment;
    }
}
