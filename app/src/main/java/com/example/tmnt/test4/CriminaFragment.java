package com.example.tmnt.test4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by tmnt on 2016/1/24.
 */
public class CriminaFragment extends Fragment {
    private Crime crime;
    private Button btnDate;
    private CheckBox isSolved;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.crimina_fragment, container, false);
        EditText crimeContent = (EditText) view.findViewById(R.id.crimeContent);
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
        Date date = new Date(System.currentTimeMillis());
        crime.setDate(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
        String s = simpleDateFormat.format(crime.getDate());
        btnDate = (Button) view.findViewById(R.id.crimineDate);
        isSolved = (CheckBox) view.findViewById(R.id.isSolved);
        btnDate.setText(s);
        btnDate.setEnabled(false);
        isSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
            }
        });
        return view;
    }
}
