package com.test.choyzer.mysaytake3.Model.Authentication;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by CheziAndSima on 29/06/2015.
 */
public class TokenGetter {

    private static final String targetURL = "https://mysay-dbtest1.rhcloud.com/api-token-auth/";

    //private static String payload = "{\"password\": \"050788\", \"username\": \"chezi\"}";
    public static String executePost(JSONObject Credentials) {
      //  UsuarioSenha usuariosenha;
       // usuariosenha = new UsuarioSenha();
       // usuariosenha.username = "myusername";
        //usuariosenha.password = "mypassword";
        StringBuffer response = new StringBuffer();
        //Gson gson = new Gson();
        //String user_pass_json = gson.toJson(usuariosenha);


        HttpURLConnection httpConnection = null;
        try {

            URL tagetUrl = new URL(targetURL);
            httpConnection = (HttpURLConnection) tagetUrl.openConnection();

            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.connect();



//            OutputStream outputStream = httpConnection.getOutputStream();
//            outputStream.write(payload);
//            outputStream.flush();

            OutputStream os = httpConnection.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
            pw.write(Credentials.toString());
            pw.close();

            if (httpConnection.getResponseCode() != 200) {
                return ("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }


            InputStream is = httpConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "MalformedURLException";

        } catch (IOException e) {
            e.printStackTrace();
            return "" + httpConnection.getErrorStream();
        } finally {

            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
    }
}
