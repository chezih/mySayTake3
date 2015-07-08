package com.test.choyzer.mysaytake3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.choyzer.mysaytake3.Activities.LoginActivity;
import com.test.choyzer.mysaytake3.Activities.UserProfileActivity;
import com.test.choyzer.mysaytake3.Model.Authentication.TokenGetter;
import com.test.choyzer.mysaytake3.Model.BL;
import com.test.choyzer.mysaytake3.Model.Entities.User;
import com.test.choyzer.mysaytake3.Utils.CredentialsStorage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity {

    String result;
    BL bl = null;
    TextView tv;
    String loggedInUserName;
    String loggedToken;
    User currentUser;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // To retrieve values back
        loggedInUserName = CredentialsStorage.getFromPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_USERNAME_KEY, "");
        loggedToken = CredentialsStorage.getFromPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_TOKEN_KEY, "");

        bl = new BL();
        tv = (TextView) findViewById(R.id.infoTextArea);

        if (loggedToken == "") {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            if (loggedInUserName != "") {
                //Toast.makeText(getApplicationContext(), "Welcome back " + loggedInUserName, Toast.LENGTH_LONG).show();
//                try {
//                    getAllUsers(this.findViewById(android.R.id.content));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_userProfile) {
            try {
                getAllUsers(this.findViewById(android.R.id.content));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            intent.putExtra("currentUser", currentUser);
            startActivity(intent);
        }
        if (id == R.id.action_logOut) {
            loggedInUserName="";
            loggedToken="";
            CredentialsStorage.saveToPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_USERNAME_KEY, "");
            CredentialsStorage.saveToPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_TOKEN_KEY, "");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void getAllUsers(View view) throws JSONException, ExecutionException, InterruptedException {
        progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("טוען");
        progress.setMessage("אנא המתן...");
        progress.show();

        new GetAndDisplayAllUsersAsync().execute();
    }

    ArrayList<User> users;

    class GetAndDisplayAllUsersAsync extends AsyncTask<Void, Void, Void>

    {
        @Override
        protected void onPostExecute(Void aVoid) {

            for (int i = 0; i < users.size(); i++) {
                result += "USER #" + i + "\n";
                result += "Id: " + users.get(i).getId() + "\n";
                result += "Name: " + users.get(i).getName() + "\n\n";
            }

            result += loggedToken ;
            tv.setText(result);
        }

        @Override
        protected Void doInBackground(Void... urls) {

            try {
                users = bl.getAllUsers();
                for (User user : users) {

                    if (loggedInUserName.equals(user.getFirstName())) {
                        currentUser = user;
                    }
                    // 1 - can call methods of element

                    // ...
                }
                progress.dismiss();
//                Token = TokenGetter.executePost(new JSONObject("{\"password\": \"050788\", \"username\": \"chezi\"}"));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public void getAllBills(View view) {
    }
}
