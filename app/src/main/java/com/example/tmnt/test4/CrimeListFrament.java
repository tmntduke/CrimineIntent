package com.example.tmnt.test4;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by tmnt on 2016/1/25.
 */
public class CrimeListFrament extends ListFragment {
    private ArrayList<Crime> crimes;
    private MyAdapter myAdapter;
    public static String LIST_ITEM_ID = "crime_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Crime");
        crimes = CrimeLab.getInstance(getActivity()).getCrimes();
        myAdapter = new MyAdapter(getActivity().getApplicationContext(), crimes);
        setListAdapter(myAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MyAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Crime c = (Crime) getListAdapter().getItem(position);
        //Crime c = ((MyAdapter) getListAdapter()).getItem(position);
        //Crime c = crimes.get(position);
        //Toast.makeText(getActivity(), c.getTitle(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(LIST_ITEM_ID, c.getCrimeId());
//        Log.i("send id",c.getCrimeId().toString());
//        Log.i("onListItemClick","start");
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
