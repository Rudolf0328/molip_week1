package com.example.molip.randomPage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.molip.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NaverActivity extends AppCompatActivity {
    MyAsyncTask task;
    String food;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    TextView text9;
    TextView text10;
    String item1;
    String item2;
    String item3;
    String item4;
    String item5;
    String item6;
    String item7;
    String item8;
    String item9;
    String item10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver);
        Intent intent = getIntent();
        String food = getIntent().getStringExtra("음식");
        System.out.println(food);
        System.out.println("here2222!!!");

        text1= (TextView)findViewById(R.id.item1);
        text2= (TextView)findViewById(R.id.item2);
        text3= (TextView)findViewById(R.id.item3);
        text4= (TextView)findViewById(R.id.item4);
        text5= (TextView)findViewById(R.id.item5);
        text6= (TextView)findViewById(R.id.item6);
        task = new MyAsyncTask();
        task.execute(food);
        System.out.println("item1");

    }



    private class MyAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... para) {
            System.out.println("here1111!!!");
            String clientId = "aTSaWfizIujYql3MUQoI";//애플리케이션 클라이언트 아이디값";
            String clientSecret = "zF1lB7FkuW";//애플리케이션 클라이언트 시크릿값";
            try {
                System.out.println("try!!!!!");
                System.out.println(para[0]);
                String text = URLEncoder.encode("카이스트 "+para[0], "UTF-8");
                System.out.println(text);
                String apiURL = "https://openapi.naver.com/v1/search/local?query="+ text + "&display=5&sort=random"; // json 결과
                //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if(responseCode==200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println("here!!!");
                System.out.println(response.toString());
                String jsonstring = response.toString();
                System.out.println(jsonstring);
                JSONObject jsonObject = new JSONObject(jsonstring);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < 5; i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);

                    String getTitle = (String) object.get("title");
                    String address = (String) object.get("address");
                    String link = (String) object.get("link");

                    String titleFilter = getTitle.replaceAll("<b>", "");
                    String title = titleFilter.replaceAll("</b>", "");

                    Log.e("test", title);
                    Log.e("test", address);
                    Log.e("test", link);
                    System.out.println(title);
                    if(i == 0){
                        item1 = title;
                        item2 = address;
                    }else if(i==1){
                        item3 = title;
                        item4 = address;
                    }else if(i==2){
                        item5 = title;
                        item6 = address;
                    }else if(i==3){
                        item7 = title;
                        item8 = address;
                    }
                    else {
                        item9 = title;
                        item10 = address;
                    }

                }

            } catch (Exception e) {
                System.out.println(e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            text1.setText(item1);
            text2.setText(item2);
            text3.setText(item3);
            text4.setText(item4);
            text5.setText(item5);
            text6.setText(item6);


        }
    }



}