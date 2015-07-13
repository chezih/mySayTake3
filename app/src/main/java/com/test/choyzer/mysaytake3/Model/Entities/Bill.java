package com.test.choyzer.mysaytake3.Model.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Choyzer on 23/06/2015.
 */
public class Bill  implements Serializable {
    int id;
    String description;
    String subject;
    String status;
    int parliament_member;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getParliament_member() {
        return parliament_member;
    }

    public void setParliament_member(int parliament_member) {
        this.parliament_member = parliament_member;
    }

    public Bill(JSONObject billJson) throws JSONException {

        Iterator<?> keys = billJson.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            switch (key) {
                case "id": {
                    this.id = billJson.getInt(key);
                    break;
                }
                case "description": {
                    this.description = billJson.getString(key);
                    break;
                }
                case "subject": {
                    this.subject = billJson.getString(key);
                    break;
                }
                case "status": {
                    this.status = billJson.getString(key);
                    break;
                }
                case "parliament_member": {
                    this.parliament_member = billJson.getInt(key);
                    break;
                }
                default:
                    break;
            }
        }

    }

}