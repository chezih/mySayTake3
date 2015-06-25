package com.test.choyzer.mysaytake3.Model;

import  com.test.choyzer.mysaytake3.Model.Entities.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by CheziAndSima on 03/06/2015.
 */
public class BL {

    Communication comm;

    private final String USER_TABLE_PATH_NAME = "users";
    private final String BILL_TABLE_PATH_NAME = "bills";

    public BL() {
        comm = new Communication();
    }

//    public User getUserByID(int id) {
//        JSONObject userJson = comm.getJsonByPath(USER_TABLE_PATH_NAME);
//
//        return convertJsonToUser(userJson);
//    }

    public ArrayList<User> getAllUsers() throws JSONException {
        String usersJsonString = comm.getJsonByPath(USER_TABLE_PATH_NAME);

        ArrayList<User> users = new ArrayList<User>();
        JSONArray jsonArray = new JSONArray(usersJsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userObject =
                    jsonArray.getJSONObject(i);
            users.add(convertJsonToUser(userObject));
        }

        return users;

    }


//    public List<Bill> getAllBills() {
//        JSONObject billsJson = comm.getJsonByPath(BILL_TABLE_PATH_NAME);
//    }

//    public Bill getBillByID(int id) {
//        JSONObject userJson = comm.getJsonByPath(BILL_TABLE_PATH_NAME);
//
//        return convertJsonToBill(userJson);
//    }


    private User convertJsonToUser(JSONObject userJson) throws JSONException {

        User u = new User(userJson);
        return u;
    }

//    private Bill convertJsonToBill(JSONObject billJson) {
//        Bill b = new Bill();
//
//        return b;
//    }
}
