package com.ums.wifiprobe.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ums.wifiprobe.R;
import com.ums.wifiprobe.ui.customview.OnDialogCloseListener;
import com.ums.wifiprobe.utils.DeviceUtil;
import com.ums.wifiprobe.utils.DialogUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by luozhimin on 2018/6/29.
 */

public class RevisedTurnoverActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.ChangSubmit)
    TextView ChangSubmit;
    @BindView(R.id.edit_query)
    EditText editQuery;
    @BindView(R.id.changEn)
    TextView changEn;
    @BindView(R.id.edit_query_bilie)
    EditText editQueryBilie;
    @BindView(R.id.edit_query_mianji)
    EditText editQueryMianji;
    @BindView(R.id.head_back)
    LinearLayout headBack;

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
        int statusBarHeight = DeviceUtil.getStatusHeight(this);
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

    @OnClick({R.id.ChangSubmit,R.id.head_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ChangSubmit:
                //提醒从应用市场下载安装
                DialogUtil.showCommonDialog2(this, "   亲爱的老板，您上次预估的修正幅度为30%，确定要将修正幅度改为-30%？", new OnDialogCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {

                        }
                    }
                });
                break;
            case R.id.head_back:
                finish();
                break;
        }
    }
}
