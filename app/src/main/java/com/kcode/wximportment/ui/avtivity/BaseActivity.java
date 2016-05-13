package com.kcode.wximportment.ui.avtivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kcode.wximportment.R;

/**
 * Created by caik on 16/5/13.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    protected abstract void setContentView();

    protected void setToolBarTitle(int resId){
        toolbar.setTitle(resId);
        setSupportActionBar(toolbar);
    }

    protected void setToolbarHomeAsUp(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected <T> T find(int id){
        return (T)findViewById(id);
    }
}
