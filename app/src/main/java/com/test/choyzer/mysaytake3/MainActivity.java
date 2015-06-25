package com.test.choyzer.mysaytake3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.choyzer.mysaytake3.Model.BL;
import com.test.choyzer.mysaytake3.Model.Entities.User;

import org.json.JSONException;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    String result;
    BL bl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void getAllUsers(View view) throws JSONException {
            TextView tv = (TextView) findViewById(R.id.infoTextArea);
            ArrayList<User> users = bl.getAllUsers();

            for (int i = 0; i < users.size(); i++) {
                result += "USER #" + i + "\n";
                result += "Id: " + users.get(i).getId() + "\n";
                result += "Name: " + users.get(i).getName() + "\n\n";
            }
//            result = "";
              tv.setText("result");
//            Translate translate = new Translate();
//            translate.execute();
        }

    public void getAllBills(View view) {
    }
}
