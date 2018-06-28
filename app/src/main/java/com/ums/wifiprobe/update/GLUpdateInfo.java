package com.ums.wifiprobe.update;

import android.content.Context;

import com.ums.wifiprobe.CommonStants;
import com.ums.wifiprobe.ui.customview.DownloadDialog;
import com.ums.wifiprobe.ui.customview.OnDialogCloseListener;

/**
 * Created by chenzhy on 2018/1/16.
 */

public class GLUpdateInfo extends AppUpdateInfo {

    public GLUpdateInfo() {
        this.appName = "店知了";
        this.appKey = CommonStants.APPKEY_GELIAN;
        this.packageName = "com.gelian.shopun";
    }

    @Override
    public void showDownloadDialog(Context context, String title, OnDialogCloseListener listener) {
        dialog = new DownloadDialog(context);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        ((DownloadDialog) dialog).setOnCloseListener(listener);
        dialog.show();
    }

    @Override
    public void updateDownloadProgress(String state, int progress) {
        if(dialog==null){
            return;
        }

        ((DownloadDialog) dialog).setDownloadState(state);
        if (progress > 0)
            ((DownloadDialog) dialog).updateProgress(progress);

    }

    @Override
    public void hideDownloadDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = null;
    }
}
