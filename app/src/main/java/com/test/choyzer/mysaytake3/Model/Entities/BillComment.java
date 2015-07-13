package com.test.choyzer.mysaytake3.Model.Entities;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Choyzer on 13/07/2015.
 */
public class BillComment {

    int bill_id;
    int id;
    int user_id;
    Date date;
    String content;

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BillComment(JSONObject billJson) throws JSONException {

        Iterator<?> keys = billJson.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            switch (key) {
                case "id": {
                    this.id = billJson.getInt(key);
                    break;
                }
                case "bill": {
                    this.bill_id = billJson.getInt(key);
                    break;
                }
                case "user": {
                    this.user_id = billJson.getInt(key);
                    break;
                }
                case "date": {
                    this.date = convertToDate(billJson.getString(key));
                    break;
                }
                case "content": {
                    this.content = billJson.getString(key);
                    break;
                }
                default:
                    break;
            }
        }

    }

    private Date convertToDate(String string) {
        return getCalendarFromISO(string).getTime();
    }

    private Calendar getCalendarFromISO(String dateString) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        try {
            Date date = dateformat.parse(dateString);
            date.setHours(date.getHours() - 1);
            calendar.setTime(date);

            String test = dateformat.format(calendar.getTime());
            Log.e("TEST_TIME", test);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }
}
