package com.example.tmnt.test4;


import android.support.v4.app.Fragment;

/**
 * Created by tmnt on 2016/1/25.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new CrimeListFrament();
    }
}
