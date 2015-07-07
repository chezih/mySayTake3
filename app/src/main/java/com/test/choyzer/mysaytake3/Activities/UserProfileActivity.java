package com.test.choyzer.mysaytake3.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.choyzer.mysaytake3.Model.Entities.User;
import com.test.choyzer.mysaytake3.R;

import java.io.InputStream;


/**
 * Created by CheziAndSima on 06/07/2015.
 */
public class UserProfileActivity extends Activity {

    User currentUser;

    TextView UserProfileNameTextView;
    TextView UserProfileStatusTextView;
    TextView UserProfileAge;
    TextView UserProfileGender;
    TextView UserProfileAddress;
    TextView UserProfileParty;

    ImageView UserProfileImageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_layout);
        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("currentUser");

        UserProfileNameTextView = (TextView) findViewById(R.id.UserProfileNameTextView);
        UserProfileStatusTextView = (TextView) findViewById(R.id.UserProfileStatusTextView);
        UserProfileAge = (TextView) findViewById(R.id.UserProfileAge);
        UserProfileGender = (TextView) findViewById(R.id.UserProfileGender);
        UserProfileAddress = (TextView) findViewById(R.id.UserProfileAddress);
        UserProfileParty = (TextView) findViewById(R.id.UserProfileParty);
        UserProfileImageView = (ImageView) findViewById(R.id.UserProfileImageView);

        UserProfileNameTextView.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        UserProfileStatusTextView.setText(currentUser.getStatus());
        UserProfileAge.setText(String.valueOf(currentUser.getAge()));
        UserProfileGender.setText(currentUser.getGender().equals("M") ? "זכר" : "נקבה");
        UserProfileAddress.setText(currentUser.getAddress());
        UserProfileParty.setText(String.valueOf(currentUser.getParty()));

        new DownloadImageTask(UserProfileImageView)
                .execute(currentUser.getPhotoURL());


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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
