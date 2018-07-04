package com.ums.wifiprobe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ums.wifiprobe.R;
import com.ums.wifiprobe.utils.DeviceUtil;

import butterknife.BindView;


/**
 * Created by luozhimin on 2018/6/29.
 */

public class RevisedTurnoverActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.status_bar)
    LinearLayout statusBar;

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
        //设置状态栏高度
        int  statusBarHeight =DeviceUtil.getStatusHeight(this);
        LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        sp_params.height = statusBarHeight;
        statusBar.setLayoutParams(sp_params);


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
