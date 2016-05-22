package com.kcode.wximportment.ui.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.kcode.wximportment.R;
import com.kcode.wximportment.ui.fragment.ListFragment;

public class MainActivity extends BaseActivity {

    private ListFragment listFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarTitle(R.string.app_name);

        FloatingActionButton fab = find(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listFragment != null){
                    listFragment.onRefresh();
                }
            }
        });

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(ListFragment.class.getSimpleName());
        if(listFragment == null){

            listFragment = new ListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,listFragment,ListFragment.class.getSimpleName())
                    .commit();
        }

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.more){
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}
