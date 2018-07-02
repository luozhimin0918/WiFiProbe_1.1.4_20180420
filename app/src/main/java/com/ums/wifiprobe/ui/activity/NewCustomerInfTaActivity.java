package com.ums.wifiprobe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ums.wifiprobe.R;
import com.ums.wifiprobe.ui.fragment.PassengerFlowDataFragment;
import com.ums.wifiprobe.ui.fragment.PassengerFlowTraFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by luozhimin on 2018/6/29.
 */

public class NewCustomerInfTaActivity extends BaseActivity implements View.OnClickListener,OnTabSelectListener {


    @BindView(R.id.head_setting)
    ImageButton headSetting;
    @BindView(R.id.h5_app_name)
    TextView h5AppName;
    @BindView(R.id.head_advanced)
    LinearLayout headAdvanced;
    @BindView(R.id.head_part)
    RelativeLayout headPart;
    @BindView(R.id.tab_main)
    SlidingTabLayout tabMain;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表
    private PassengerFlowTraFragment passengerFlowTraFragment;
    private PassengerFlowDataFragment passengerFlowDataFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView() {
        passengerFlowTraFragment=new PassengerFlowTraFragment();
        passengerFlowDataFragment=new PassengerFlowDataFragment();
        list_fragment=new ArrayList<Fragment>();
        list_fragment.add(passengerFlowTraFragment);

        list_fragment.add(passengerFlowDataFragment);
        //tab title List
        list_title=new ArrayList<String>();
        list_title.add("客流交易数据");
        list_title.add("客流数据");
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabMain.setViewPager(viewPager);
        tabMain.setOnTabSelectListener(this);
        tabMain.showDot(0);
        viewPager.setCurrentItem(0);
        tabMain.showMsg(1, 5);
        tabMain.setMsgMargin(1, 12.0f, 10.0f);


    }

    @Override
    public void initData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_main;
    }

    @Override
    public void CacheClearComplete() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }

    }

    @OnClick({R.id.head_setting, R.id.head_advanced})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_setting:
                break;
            case R.id.head_advanced:
                break;
        }
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_title.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }
    }

}
