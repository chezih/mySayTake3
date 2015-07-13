package com.test.choyzer.mysaytake3.Model;

import android.app.Application;

import com.test.choyzer.mysaytake3.Model.Entities.User;

import java.util.ArrayList;

/**
 * Created by CheziAndSima on 13/07/2015.
 */
public class GlobalData extends Application {

    private ArrayList<User> users;

    public ArrayList<User> getSavedUsers() {
        return users;
    }

    public void setSavedUsers(ArrayList<User> users) {
        this.users = users;
    }
}