package com.example.tmnt.test4;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tmnt on 2016/1/24.
 */
public class CriminaFragment extends Fragment {
    private Crime crime;
    private Button btnDate;
    private CheckBox isSolved;
    //private EditText crimeTitle;
    private UUID id;
    public static int REQUEST = 0;
    private CrimeLab lab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //crime = new Crime();
        id = (UUID) getArguments().getSerializable(CrimeListFrament.LIST_ITEM_ID);
        //lab = CrimeLab.getInstance(getActivity());
        crime = CrimeLab.getInstance(getActivity()).getCrime(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.crimina_fragment, container, false);
        EditText crimeContent = (EditText) view.findViewById(R.id.crimeContent);
        crimeContent.setText(crime.getTitle());
        crimeContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                crime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
//        Date date = new Date(System.currentTimeMillis());
//        crime.setDate(date);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
//        String s = simpleDateFormat.format(crime.getDate());
        //String s = getSimpleDate(crime.getDate());
        btnDate = (Button) view.findViewById(R.id.crimineDate);
        isSolved = (CheckBox) view.findViewById(R.id.isSolved);
        //btnDate.setText(s);
        if (CrimeLab.getInstance(getActivity()).getCrimes().size() == 1) {
            updateDate(new Date(System.currentTimeMillis()));
        } else {
            updateDate(crime.getDate());
        }

        btnDate.setEnabled(true);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                DialogPickerFragment fragment;
                //DialogPickerFragment fragment = new DialogPickerFragment();
                if (CrimeLab.getInstance(getActivity()).getCrimes().size() == 1) {
                    fragment = DialogPickerFragment.newInstance(new Date(System.currentTimeMillis()));
                } else {
                    fragment = DialogPickerFragment.newInstance(crime.getDate());
                }

                fragment.setTargetFragment(CriminaFragment.this, REQUEST);
                //Bundle bundle=new Bundle();
                //bundle.putSerializable("returnId",crime.getCrimeId());
                //fragment.setArguments(bundle);
                fragment.show(manager, "date");
            }
        });
        isSolved.setChecked(crime.isSolved());
        isSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
            }
        });
        return view;
    }

    public static String getSimpleDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
        String s = simpleDateFormat.format(date);
        return s;
    }

    public static CriminaFragment newInstance(UUID getId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CrimeListFrament.LIST_ITEM_ID, getId);
        CriminaFragment fragment = new CriminaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link Activity#onPause() Activity.onPause} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.getInstance(getActivity()).saveCrimeLab();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CriminaFragment.REQUEST) {
                Date date = (Date) data.getSerializableExtra(DialogPickerFragment.EXTRA_DATE);
                crime.setDate(date);
                String s = CriminaFragment.getSimpleDate(date);
                //((MyAdapter) getListAdapter()).notifyDataSetChanged();
                //updateDate(date);
            }
        }
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void updateDate(Date date) {
        String s = getSimpleDate(date);
        btnDate.setText(s);
    }
}
