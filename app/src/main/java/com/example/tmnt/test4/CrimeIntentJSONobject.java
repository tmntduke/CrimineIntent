package com.example.tmnt.test4;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by tmnt on 2016/1/30.
 */
public class CrimeIntentJSONobject {
    private Context context;
    private String fileName;
    public CrimeIntentJSONobject(Context context,String fileName){
        this.context=context;
        this.fileName=fileName;
    }
    public void saveCrime(ArrayList<Crime>crimes) throws JSONException {
        JSONArray jsonArray=new JSONArray();
        String s=null;
        FileOutputStream outputStream=null;
        for (Crime c:crimes){
            jsonArray.put(c.toJso());
        }
        try {
             outputStream=context.openFileOutput(fileName,Context.MODE_PRIVATE);
            outputStream.write(jsonArray.toString().getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public ArrayList<Crime> loadCrime(){
        ArrayList<Crime> crimes=new ArrayList<>();
        BufferedReader buffer=null;
        try {
            InputStream inputStream=context.openFileInput("Crime.json");
            buffer=new BufferedReader(new InputStreamReader(inputStream));
            String line=null;
            StringBuffer stringBuffer=new StringBuffer();
            while ((line=buffer.readLine())!=null){
                stringBuffer.append(line);
            }
            JSONArray jsonArray=(JSONArray)new JSONTokener(stringBuffer.toString()).nextValue();
            for (int i=0;i<jsonArray.length();i++){
                crimes.add(new Crime(jsonArray.getJSONObject(i)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            if (buffer!=null){
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return crimes;
    }
}
