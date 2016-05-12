package com.kcode.wximportment.ui.avtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kcode.wximportment.R;
import com.kcode.wximportment.ui.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(ListFragment.class.getSimpleName());
        if(listFragment == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new ListFragment(),ListFragment.class.getSimpleName())
                    .commit();
        }

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

            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}
