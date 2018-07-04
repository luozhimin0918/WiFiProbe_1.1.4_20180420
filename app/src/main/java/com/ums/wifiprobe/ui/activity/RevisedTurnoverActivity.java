package com.ums.wifiprobe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ums.wifiprobe.R;


/**
 * Created by luozhimin on 2018/6/29.
 */

public class RevisedTurnoverActivity extends BaseActivity implements View.OnClickListener {




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


    }

    @Override
    public void initData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_revised_turnover;
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

}
