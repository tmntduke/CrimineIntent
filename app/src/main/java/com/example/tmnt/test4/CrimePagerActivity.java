package com.example.tmnt.test4;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by tmnt on 2016/1/26.
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager viewPager;
    private ArrayList<Crime> crimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);
        crimes = CrimeLab.getInstance(this).getCrimes();
        FragmentManager manager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = crimes.get(position);
                return CriminaFragment.newInstance(crime.getCrimeId());
            }

            @Override
            public int getCount() {
                return crimes.size();
            }
        });
        UUID id = (UUID) getIntent().getSerializableExtra(CrimeListFrament.LIST_ITEM_ID);
        for (int i = 0; i < crimes.size(); i++) {
            if (crimes.get(i).getCrimeId().equals(id)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime crime = crimes.get(position);
                if (crime.getTitle() != null) {
                    setTitle(crime.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
