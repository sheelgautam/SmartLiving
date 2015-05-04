package com.evssmart.sheel.smartliving;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;


public class control extends ActionBarActivity implements ActionBar.TabListener {

    ActionBar actionBar;
    ViewPager viewPager;
    public static boolean running = true;

    public control() {
        //Requires empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        Toast.makeText(this, "Connected!", Toast.LENGTH_SHORT).show();

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                actionBar.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab switch_tab = actionBar.newTab();
        switch_tab.setText("Appliances");
        switch_tab.setTabListener(this);

        ActionBar.Tab data_tab = actionBar.newTab();
        data_tab.setText("Sensors");
        data_tab.setTabListener(this);

        ActionBar.Tab about_tab = actionBar.newTab();
        about_tab.setText("About");
        about_tab.setTabListener(this);

        actionBar.addTab(switch_tab);
        actionBar.addTab(data_tab);
        actionBar.addTab(about_tab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment = null;
            if(i == 0) {
                fragment = new SwitchFragment();
            } else if(i == 1) {
                fragment = new DataFragment();
            } else if(i == 2) {
                fragment = new AboutFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            MainActivity.socketClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //DataFragment.getData.cancel(true);
        running = false;
        this.finish();
    }
}
