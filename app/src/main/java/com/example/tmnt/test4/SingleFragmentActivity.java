package com.example.tmnt.test4;



import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by tmnt on 2016/1/25.
 */
public abstract class SingleFragmentActivity extends FragmentActivity{
    public abstract Fragment createFragment();
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        actionBar=getActionBar();
//        actionBar.show();
       // getActionBar().show();
        FragmentManager manager =getSupportFragmentManager() ;
        Fragment fragment = manager.findFragmentById(R.id.CriminaFragmentContanir);
        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction().add(R.id.CriminaFragmentContanir, fragment).commit();
        }
    }
}
