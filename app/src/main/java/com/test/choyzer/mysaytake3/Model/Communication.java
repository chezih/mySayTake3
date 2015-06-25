package com.test.choyzer.mysaytake3.Model;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.lang.String;
import java.lang.StringBuilder;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by CheziAndSima on 03/06/2015.
 */
public class Communication {

    private final String REST_BASE_URL = "https://mysay-dbtest1.rhcloud.com/api/v1/";

    String jsonString;
    String userPath;

    public String getJsonByPath(String path) throws ExecutionException, InterruptedException {

        userPath = REST_BASE_URL + path;

        GetJsonAsync getJsonAsync = new GetJsonAsync();
        getJsonAsync.execute();
        String s = new GetJsonAsync().execute(userPath).get();
        return s;
    }


    class GetJsonAsync extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String aVoid) {

        }

        @Override
        protected String doInBackground(String... urls) {

            jsonString = getJSON(urls[0], 5000);
            return jsonString;
        }

        public String getJSON(String url, int timeout) {
            HttpURLConnection c = null;
            try {
                URL u = new URL(url);
                c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setRequestProperty("Content-length", "0");
                c.setRequestProperty("Content-Type", "text/json; charset=utf-8");
                c.setUseCaches(false);
                c.setAllowUserInteraction(false);
                c.setConnectTimeout(timeout);
                c.setReadTimeout(timeout);
                c.connect();
                int status = c.getResponseCode();

                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        br.close();
                        return sb.toString();
                }

            } catch (MalformedURLException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
//            } catch (ProtocolException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
            } finally {
                if (c != null) {
                    try {
                        c.disconnect();
                    } catch (Exception ex) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            return null;
        }
    }
}
