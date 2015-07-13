package com.test.choyzer.mysaytake3.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.test.choyzer.mysaytake3.Adapters.BillRowAdapter;
import com.test.choyzer.mysaytake3.Model.BL;
import com.test.choyzer.mysaytake3.Model.Entities.Bill;
import com.test.choyzer.mysaytake3.Model.Entities.User;
import com.test.choyzer.mysaytake3.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Choyzer on 13/07/2015.
 */
public class BillsActivity extends Activity {


    ProgressDialog progress;
    ArrayList<Bill> arrayOfBills;
    ListView listView;
    BL bl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        bl = new BL();
        progress = new ProgressDialog(BillsActivity.this);
        progress.setTitle(getString(R.string.Login_dialog_head));
        progress.setMessage(getString(R.string.Login_dialog_message));
        progress.show();

        // Construct the data source
        arrayOfBills = new ArrayList<Bill>();
// Create the adapter to convert the array to views

// Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.billsListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bill currentBill = arrayOfBills.get(position);
                Intent intent = new Intent(BillsActivity.this, BillDetailActivity.class);
                intent.putExtra("currentBill",currentBill);
                startActivity(intent);
            }
        });

        new GetAllBillsAsync().execute();


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


    class GetAllBillsAsync extends AsyncTask<Void, Void, Void>

    {
        @Override
        protected void onPostExecute(Void aVoid) {

            BillRowAdapter adapter = new BillRowAdapter(BillsActivity.this, arrayOfBills);
            listView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... urls) {

            try {
                arrayOfBills = bl.getAllBills();
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


}
