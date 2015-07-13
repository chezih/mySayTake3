package com.test.choyzer.mysaytake3.Model;

import com.test.choyzer.mysaytake3.Model.Entities.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by CheziAndSima on 03/06/2015.
 */
public class BL {

    Communication comm;

    private final String USER_TABLE_PATH_NAME = "users";
    private final String BILL_TABLE_PATH_NAME = "bills";
    private final String BILL_COMMENT_TABLE_PATH_NAME = "billComments";

    public BL() {
        comm = new Communication();
    }

//    public User getUserByID(int id) {
//        JSONObject userJson = comm.getJsonByPath(USER_TABLE_PATH_NAME);
//
//        return convertJsonToUser(userJson);
//    }

    public ArrayList<User> getAllUsers() throws JSONException, ExecutionException, InterruptedException {
        String usersJsonString = comm.getJsonByPath(USER_TABLE_PATH_NAME);

        ArrayList<User> users = new ArrayList<User>();
        JSONArray jsonArray = new JSONArray(usersJsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userObject =
                    jsonArray.getJSONObject(i);
            users.add(new User(userObject));
        }

        return users;

    }

    public ArrayList<Bill> getAllBills() throws JSONException, ExecutionException, InterruptedException {
        String billsJsonString = comm.getJsonByPath(BILL_TABLE_PATH_NAME);

        ArrayList<Bill> bills = new ArrayList<Bill>();
        JSONArray jsonArray = new JSONArray(billsJsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject billObject =
                    jsonArray.getJSONObject(i);
            bills.add(new Bill(billObject));
        }

        return bills;

    }


    public ArrayList<BillComment> getBillsComments(int id) throws ExecutionException, InterruptedException, JSONException {

        String billsCommentsJsonString = comm.getJsonByPath(BILL_COMMENT_TABLE_PATH_NAME +"/?bill_id="+String.valueOf(id));

        ArrayList<BillComment> billsComments = new ArrayList<BillComment>();
        JSONArray jsonArray = new JSONArray(billsCommentsJsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject billObject =
                    jsonArray.getJSONObject(i);
            billsComments.add(new BillComment(billObject));
        }
        return billsComments;

    }
}
