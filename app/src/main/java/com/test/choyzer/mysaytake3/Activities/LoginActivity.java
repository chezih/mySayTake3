package com.test.choyzer.mysaytake3.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.choyzer.mysaytake3.MainActivity;
import com.test.choyzer.mysaytake3.Model.Authentication.TokenGetter;
import com.test.choyzer.mysaytake3.R;
import com.test.choyzer.mysaytake3.Utils.CredentialsStorage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;


public class LoginActivity extends Activity {


    EditText userNameEditText;
    EditText passwordEditText;

    String TokenJson;
    String Token;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
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

    public void Login(View view) {
        new LoginAsync().execute();
    }

    class LoginAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {

            try {
                JSONObject tokenJsonObject = new JSONObject(TokenJson);
                Iterator<?> keys = tokenJsonObject.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    Token = tokenJsonObject.getString(key);
                    Toast.makeText(getApplicationContext(), "Authentication successful with token: " + Token, Toast.LENGTH_LONG).show();
                    CredentialsStorage.saveToPrefs(LoginActivity.this, CredentialsStorage.PREFS_LOGIN_USERNAME_KEY, userNameEditText.getText().toString());
                    CredentialsStorage.saveToPrefs(LoginActivity.this, CredentialsStorage.PREFS_LOGIN_TOKEN_KEY, Token);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Void doInBackground(Void... urls) {

            String userName = userNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            try {
                TokenJson = TokenGetter.executePost(new JSONObject("{\"password\": \"" + password + "\", \"username\": \"" + userName + "\"}"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
