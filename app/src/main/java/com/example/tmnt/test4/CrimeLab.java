package com.example.tmnt.test4;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tmnt on 2016/1/25.
 * 用来保存在列表视图中的列表项对象
 */
public class CrimeLab {
    private static CrimeLab sCrime;
    private Context context;
    private ArrayList<Crime> list;

    /**
     * @param context 完成启动Activity等
     *                创建CrimeLab构造方法，在其中进行对Crime对象的创建
     */
    private CrimeLab(Context context) {
        this.context = context;
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0);
            c.setDate(new Date(System.currentTimeMillis()));
            list.add(c);
        }
    }

    /**
     * @return 返回列表项对象（多个Crime对象）
     */
    public ArrayList<Crime> getCrimes() {
        return list;
    }

    /**
     * 创建CrimeLab单例
     *
     * @param c
     * @return 返回C日么Lob对象，并保证值创建一个对象
     */
    public static CrimeLab getInstance(Context c) {
        if (sCrime == null) {
            sCrime = new CrimeLab(c.getApplicationContext());
        }
        return sCrime;
    }

    /**
     * 获得指定Id的Crime对象
     *
     * @param id 查询的Id
     * @return 返回指定Id的Crime对象，找不到指定Id就返回空
     */
    public Crime getCrime(UUID id) {
        for (Crime c : list) {
            if (c.getCrimeId().equals(id)) {
                return c;
            }
        }
        return null;
    }
}
