package com.test.choyzer.mysaytake3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    String Token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // To retrieve values back
        String loggedInUserName = CredentialsStorage.getFromPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_USERNAME_KEY, "");
        String loggedToken = CredentialsStorage.getFromPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_TOKEN_KEY, "");

        if (loggedToken == "") {
            //TODO call login
            Intent intent = new Intent(rfgfdgf);
        }

        bl = new BL();
        tv = (TextView) findViewById(R.id.infoTextArea);
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

        return super.onOptionsItemSelected(item);
    }

    public void getAllUsers(View view) throws JSONException, ExecutionException, InterruptedException {
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

            result += Token;
            tv.setText(result);
        }

        @Override
        protected Void doInBackground(Void... urls) {

            try {
                users = bl.getAllUsers();
                 Token = TokenGetter.executePost(new JSONObject("{\"password\": \"050788\", \"username\": \"chezi\"}"));
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
