package com.web.beacon_app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class SigninActivity  extends AsyncTask{
   private TextView mainLogo;
   private Context context;
   private ArrayList memberList;

   //flag 0 means get and 1 means post.(By default it is get.)
   public SigninActivity(Context context, TextView mainLogo) {
      this.context = context;
      this.mainLogo = mainLogo;
   }

   protected void onPreExecute(){
   }


   @Override
   protected Object doInBackground(Object... arg0) {

         try{
            String name = arg0[0].toString();
            String birth = arg0[1].toString();
            String gender = arg0[2].toString();
            String tel = arg0[3].toString();

            String link="http://192.168.219.101/login.php";
            String data  = URLEncoder.encode("userName", "UTF-8") + "=" +
               URLEncoder.encode(name, "UTF-8");
            data += "&" + birth;
            data += "&" + URLEncoder.encode("userGender", "UTF-8") + "=" +
                    URLEncoder.encode(gender, "UTF-8");
            data += "&" + tel;

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
               InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
               sb.append(line);
               break;
            }
				
            return sb.toString();
         } catch(Exception e){
            return new String("Exception: " + e.getMessage());
         }
   }

   @Override
   protected void onPostExecute(Object result){
      this.mainLogo.setText(result.toString());
   }
}