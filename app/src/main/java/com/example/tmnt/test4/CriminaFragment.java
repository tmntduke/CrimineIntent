package com.example.tmnt.test4;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

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
    private static int REQUEST = 0;
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
//        Date date = new Date(System.currentTimeMillis());
//        crime.setDate(date);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
//        String s = simpleDateFormat.format(crime.getDate());
        //String s = getSimpleDate(crime.getDate());
        btnDate = (Button) view.findViewById(R.id.crimineDate);
        isSolved = (CheckBox) view.findViewById(R.id.isSolved);
        //btnDate.setText(s);
        updateDate(crime.getDate());
        btnDate.setEnabled(true);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                //DialogPickerFragment fragment = new DialogPickerFragment();
                DialogPickerFragment fragment = DialogPickerFragment.newInstance(crime.getDate());
                fragment.setTargetFragment(CriminaFragment.this, REQUEST);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST) {
                Date date = (Date) data.getSerializableExtra(DialogPickerFragment.EXTRA_DATE);
                //crime.setDate(date);
                updateDate(date);
            }
        }
    }

    public void updateDate(Date date) {
        String s = getSimpleDate(crime.getDate());
        btnDate.setText(s);
    }
}
