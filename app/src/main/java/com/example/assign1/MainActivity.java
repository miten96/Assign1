package com.example.assign1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    int currentPage;
    static final String[] pageNames = {"Angus","Hereford","Brahman","Shorthotn","Brangus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragment_home frag = new fragment_home();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.cowPlace, frag);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void cowbuttonClick (View view) {
        currentPage = Integer.valueOf((String)view.getTag());
        showCurrentPage(currentPage);
    }

    public void showCurrentPage (int tagID) {
        if (tagID == 0) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragment_home hf = new fragment_home();
            ft.replace(R.id.cowPlace, hf);
            ft.commit();
        } else {
            CowFragment frag = new CowFragment();
            //Communicate the cow breed to the fragment
            Bundle args = new Bundle();
            args.putInt("cow", tagID);
            frag.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cowPlace, frag).commit();
        }
    }

    public void clickPrev (View view) {
        currentPage = currentPage - 1;
        if (currentPage <= 0) {
            currentPage = 5;
        }
        showCurrentPage(currentPage);
    }

    public void clickNext (View view) {
        currentPage = currentPage + 1;
        if (currentPage > 5) {
            currentPage = 1;
        }

        showCurrentPage(currentPage);
    }

    public void clickHome (View view) {
        currentPage = 0;
        showCurrentPage(currentPage);
    }
}
