package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import android.content.Intent;
import android.os.Bundle;

import com.gao.yingjian.mobiledevelopmentassignmentone.Adapters.MainScreenAdapter;
import com.gao.yingjian.mobiledevelopmentassignmentone.DAOs.LogInfo;
import com.gao.yingjian.mobiledevelopmentassignmentone.Models.MainScreen;
import com.gao.yingjian.mobiledevelopmentassignmentone.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.IdRes;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;

import org.litepal.LitePal;

import java.util.ArrayList;

public class HomeNavigationDrawerActivity extends ActivityBase
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    public static final String INTENT_EXTRA_USER ="user";
    private static final String TAG = "HomeNavigationDrawerActivity";

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPagerNavigation;

    private MainScreenAdapter mainScreenAdapter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation_drawer);

        viewPagerNavigation = findViewById(R.id.viewPagerNavigation);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ArrayList<MainScreen> mainScreens = this.getMainScrees();

        MainScreen defaultScreen = null;
        defaultScreen = getMainScreen(mainScreens, R.id.homeMenuItem);
        mainScreenAdapter = new MainScreenAdapter(getSupportFragmentManager(), 1);
        mainScreenAdapter.setItems(mainScreens);

        bottomNavigationView.setSelectedItemId(defaultScreen.getMenuItemId());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        viewPagerNavigation.setAdapter(mainScreenAdapter);
        viewPagerNavigation.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MainScreen selectedScreen = mainScreenAdapter.getItems().get(position);
                selectBottomNavigationViewMenuItem(selectedScreen.getMenuItemId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        scrollToScreen(defaultScreen);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        username = getIntent().getStringExtra(INTENT_EXTRA_USER);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            this.finishAffinity();
            //System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settingsMenuItem) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        MainScreen it = getMainScreen(mainScreenAdapter.getItems(), id);
        if(it != null) {
            scrollToScreen(it);
            return true;
        }

        if(id == R.id.clearLoggedInUsers){
            LitePal.deleteAll(LogInfo.class);
        }
        if(id == R.id.logoutMenuItem){
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ArrayList<MainScreen> getMainScrees(){
        ArrayList<MainScreen> mainScreens = new ArrayList<>();
        mainScreens.add(new MainScreen(R.id.recentMenuItem, R.drawable.ic_recent, R.string.home_bottom_navigation_recent_title_name, RecentFragment.newInstance()));
        mainScreens.add(new MainScreen(R.id.homeMenuItem, R.drawable.ic_home, R.string.home_bottom_navigation_home_title_name, HomeFragment.newInstance()));
        mainScreens.add(new MainScreen(R.id.notificationMenuItem, R.drawable.ic_notifications, R.string.home_bottom_navigation_notification_title_name, NotificationFragment.newInstance()));
        return mainScreens;
    }

    private void scrollToScreen(MainScreen mainScreen) {
        int screenPosition = mainScreenAdapter.getItems().indexOf(mainScreen);
        if (screenPosition != viewPagerNavigation.getCurrentItem()) {
            viewPagerNavigation.setCurrentItem(screenPosition);
        }
    }

    private void selectBottomNavigationViewMenuItem(@IdRes int menuItemId) {
        bottomNavigationView.setOnNavigationItemSelectedListener(null);
        bottomNavigationView.setSelectedItemId(menuItemId);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private MainScreen getMainScreen(ArrayList<MainScreen> mainScreens, @IdRes int menuItemId) {
        MainScreen defaultScreen = null;
        for( MainScreen screen : mainScreens){
            if(screen.getMenuItemId() == menuItemId){
                defaultScreen = screen;
            }
        }
        return defaultScreen;
    }

}
