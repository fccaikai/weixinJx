package com.kcode.wximportment.ui.avtivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.kcode.wximportment.R;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarTitle(R.string.home_page);
        setToolbarHomeAsUp();

        TextView mVersion = find(R.id.version);
        mVersion.setText("版本号:" + getVersion());

        TextView mUrl = find(R.id.url);
        mUrl.setText("开源地址:https://github.com/fccaikai/weixinJx");
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_about);
    }

    private String getVersion(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }
}
