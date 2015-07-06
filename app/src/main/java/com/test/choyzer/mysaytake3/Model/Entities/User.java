package com.test.choyzer.mysaytake3.Model.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Choyzer on 23/06/2015.
 */
public class User implements Serializable {
    int id;
    String firstName;
    String lastName;
    String email;
    String address;
    int party;
    int age;
    String photoURL;
    String status;
    String gender;

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
                    this.firstName = userJson.getString(key);
                    break;
                }
                case "last_name": {
                    this.lastName = userJson.getString(key);
                    break;
                }
                case "e_mail": {
                    this.email = userJson.getString(key);
                    break;
                }
                case "address":
                {
                    this.address = userJson.getString(key);
                    break;
                }
                case "party": {
                    this.party = userJson.getInt(key);
                    break;
                }
                case "photo": {
                    this.photoURL = userJson.getString(key);
                    break;
                }
                case "status": {
                    this.status = userJson.getString(key);
                    break;
                }
                case "age": {
                    this.age = userJson.getInt(key);
                    break;
                }
                case "gender": {
                    this.gender = userJson.getString(key);
                    break;
                }
                default:
                    break;
            }
        }

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getParty() {
        return party;
    }

    public void setParty(int party) {
        this.party = party;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return firstName;
    }

    public int getId() {
        return id;
    }


}
