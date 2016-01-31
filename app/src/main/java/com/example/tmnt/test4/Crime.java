package com.example.tmnt.test4;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tmnt on 2016/1/24.
 */
public class Crime {
    private UUID crimeId;
    private String title;
    private String content;
    private Date date;
    private boolean solved;

    public Crime() {
        crimeId = UUID.randomUUID();
    }

    public Crime(JSONObject jsonObject) throws JSONException, ParseException {
        crimeId = UUID.fromString(jsonObject.getString("id"));
        if (jsonObject.getString("title") != null) {
            title = jsonObject.getString("title");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date d = simpleDateFormat.parse(jsonObject.getString("date"));
        date = d;
        solved = jsonObject.getBoolean("solved");
    }

    public Crime(UUID crimeId, String title, String content, Date date, boolean solved) {
        this.date = date;
        this.solved = solved;
        this.crimeId = crimeId;
        this.title = title;
        this.content = content;
    }

    public UUID getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(UUID crimeId) {
        this.crimeId = crimeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public JSONObject toJso() throws JSONException {
        JSONObject jsonObjec = new JSONObject();
        jsonObjec.put("id", crimeId.toString());
        jsonObjec.put("title", title);
        jsonObjec.put("content", content);
        jsonObjec.put("date", date);
        jsonObjec.put("solve", solved);
        return jsonObjec;
    }
}
