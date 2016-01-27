package com.example.tmnt.test4;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.usage.UsageEvents;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.UUID;


public class CriminaActivity extends SingleFragmentActivity {


    @Override
    public android.support.v4.app.Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeListFrament.LIST_ITEM_ID);
//        Log.i("return",crimeId.toString());
        return CriminaFragment.newInstance(crimeId);
    }
}
