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
import android.widget.Toast;

public class SigninActivity  extends AsyncTask{

   Context context;

   public SigninActivity(Context context) {
      this.context = context;
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

            String link="http://192.168.219.101/insertValue.php?userName="+name+"&userBirth="+birth+"&userGender="+gender+"&userTel="+tel;
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line="";

            while ((line = in.readLine()) != null) {
               sb.append(line);
               break;
            }

            in.close();
            return sb.toString();
         } catch(Exception e){
            return new String("Exception: " + e.getMessage());
         }
   }

   @Override
   protected void onPostExecute(Object result){

       if(!result.toString().equals("Values have been inserted successfully")){
           Toast.makeText(context, "이미 등록된 사용자 입니다.", Toast.LENGTH_LONG).show();
       }else{
           Toast.makeText(context, "사용자 등록이 완료 되었습니다.", Toast.LENGTH_LONG).show();
       }

   }
}