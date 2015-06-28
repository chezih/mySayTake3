package com.test.choyzer.mysaytake3.Model.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Choyzer on 23/06/2015.
 */
public class User {
    int id;
    String name;

    public User(JSONObject userJson) throws JSONException {

        Iterator<?> keys = userJson.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            switch (key) {
                case "id": {
                    this.id = userJson.getInt(key);
                    break;
                }
                case "first_name": {
                    this.name = userJson.getString(key);
                    break;
                }
                default:
                    break;
            }
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
