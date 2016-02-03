package com.example.tmnt.test4;

import android.support.v4.app.Fragment;

/**
 * Created by tmnt on 2016/2/1.
 */
public class CrimeCameraActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CrimeCameraFragment();
    }
}
