package com.example.molip.randomPage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.molip.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class RouletteActivity extends AppCompatActivity{
    private SwipeRefreshLayout swipeRefreshLayout;
    ImageView back;
    ImageView ranimg;
    TextView text;
    MyAsyncTask task;
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

    String link1, link2, link3, link4, link5;

    ViewFlipper vfllipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        System.out.println("tab3");
        back = (ImageView) findViewById(R.id.foodimage);
        text= (TextView)findViewById(R.id.textfood);
        text.setText("아래로 스와이프!");
        Drawable foodimg = getResources().getDrawable(R.drawable.food);

        back.setImageDrawable(foodimg);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        System.out.println("4!!!");

        //합치는 부분
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
        text7= (TextView)findViewById(R.id.item7);
        text8= (TextView)findViewById(R.id.item8);
        text9= (TextView)findViewById(R.id.item9);
        text10= (TextView)findViewById(R.id.item10);
        System.out.println(link1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(link1);
                if(link1.equals("")) {
                    System.out.println("else");
                    Toast.makeText(RouletteActivity.this, "링크가 없어요.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                    intent1.setData(Uri.parse(link1));
                    startActivity(intent1);
                }
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(link2);
                if(link2.equals("")) {
                    System.out.println("else");
                    Toast.makeText(RouletteActivity.this, "링크가 없어요.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Intent intent2 = new Intent(Intent.ACTION_VIEW);
                    intent2.setData(Uri.parse(link2));
                    startActivity(intent2);
                }
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(link3);
                if(link3.equals("")) {
                    System.out.println("else");
                    Toast.makeText(RouletteActivity.this, "링크가 없어요.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Intent intent3 = new Intent(Intent.ACTION_VIEW);
                    intent3.setData(Uri.parse(link3));
                    startActivity(intent3);
                }
            }
        });
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(link4);
                if(link4.equals("")) {
                    System.out.println("else");
                    Toast.makeText(RouletteActivity.this, "링크가 없어요.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Intent intent4 = new Intent(Intent.ACTION_VIEW);
                    intent4.setData(Uri.parse(link4));
                    startActivity(intent4);
                }
            }
        });
        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(link5);
                if(link5.equals("")) {
                    System.out.println("else");
                    Toast.makeText(RouletteActivity.this, "링크가 없어요.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Intent intent5 = new Intent(Intent.ACTION_VIEW);
                    intent5.setData(Uri.parse(link5));
                    startActivity(intent5);
                }
            }
        });

//        task = new RouletteActivity.MyAsyncTask();
//        task.execute(food);
        System.out.println("item1");

        //합치는 부분

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ImageView a1=(ImageView)findViewById(R.id.ran1);
                ImageView a2=(ImageView)findViewById(R.id.ran2);
                ImageView a3=(ImageView)findViewById(R.id.ran3);
                ImageView a4=(ImageView)findViewById(R.id.ran4);
                ImageView a5=(ImageView)findViewById(R.id.ran5);
                ImageView a6=(ImageView)findViewById(R.id.ran6);
                ImageView a7=(ImageView)findViewById(R.id.ran7);
                ImageView a8=(ImageView)findViewById(R.id.ran8);
                ImageView a9=(ImageView)findViewById(R.id.ran9);
                ImageView a10=(ImageView)findViewById(R.id.ran10);

                back.setImageResource(0);

                a1.setImageResource(0);
                a2.setImageResource(0);
                a3.setImageResource(0);
                a4.setImageResource(0);
                a5.setImageResource(0);
                a6.setImageResource(0);
                a7.setImageResource(0);
                a8.setImageResource(0);
                a9.setImageResource(0);
                a10.setImageResource(0);

                //int[] flipimg = new int[10][2] {{R.drawable.chicken,1}, {R.drawable.pizza,2}, R.drawable.ddokk, R.drawable.rice, R.drawable.hamburger, R.drawable.sandwich, R.drawable.sushi, R.drawable.steak, R.drawable.pasta, R.drawable.bread};
                int[][] flipimg = new int[][] {{R.drawable.chicken,1}, {R.drawable.pizza,2}, {R.drawable.ddokk,3}, {R.drawable.rice,4}, {R.drawable.hamburger,5}, {R.drawable.sandwich,6}, {R.drawable.sushi,7}, {R.drawable.steak,8}, {R.drawable.pasta,9}, {R.drawable.bread,10}, {R.drawable.salad,11} , {R.drawable.curry,12},
                        {R.drawable.chinese,13}, {R.drawable.maratang,14}, {R.drawable.hotdog,15}, {R.drawable.barbeque,16}, {R.drawable.soup,17}, {R.drawable.bibimbap,18},
                        {R.drawable.dumpling,19}, {R.drawable.ricenoodle,20}, {R.drawable.knifenoodle,21}, {R.drawable.ramen,22}, {R.drawable.porkfeet,23}, {R.drawable.steamedpork,24},
                        {R.drawable.jjukkumi,25},};
                String[] fliptext = {"치킨","피자","떡볶이","백반","햄버거","샌드위치","초밥","스테이크","파스타","빵","샐러드","카레","중국집","마라탕","핫도그","고기","국밥","비빔밥","만두",
                        "쌀국수","칼국수","라멘","족발","보쌈","쭈꾸미"};
                String[] rantext = {"먹어.","어때?","먹자!","먹을까?","고고!","별로야?"};
                ranimg = (ImageView) findViewById(R.id.ranimage);
                ranimg.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                text3.setVisibility(View.INVISIBLE);
                text4.setVisibility(View.INVISIBLE);
                text5.setVisibility(View.INVISIBLE);
                text6.setVisibility(View.INVISIBLE);
                text7.setVisibility(View.INVISIBLE);
                text8.setVisibility(View.INVISIBLE);
                text9.setVisibility(View.INVISIBLE);
                text10.setVisibility(View.INVISIBLE);

                shuffleArray(flipimg,fliptext);
                a1.setImageResource(flipimg[0][0]);
                a2.setImageResource(flipimg[1][0]);
                a3.setImageResource(flipimg[2][0]);
                a4.setImageResource(flipimg[3][0]);
                a5.setImageResource(flipimg[4][0]);
                a6.setImageResource(flipimg[5][0]);
                a7.setImageResource(flipimg[6][0]);
                a8.setImageResource(flipimg[7][0]);
                a9.setImageResource(flipimg[8][0]);
                a10.setImageResource(flipimg[9][0]);

                int last = flipimg[9][1]-1;
                System.out.println(flipimg[0][1]);
                System.out.println(flipimg[1][1]);
                System.out.println(flipimg[2][1]);
                System.out.println(flipimg[3][1]);
                System.out.println(flipimg[4][1]);
                System.out.println(flipimg[5][1]);
                System.out.println(flipimg[6][1]);
                System.out.println(flipimg[7][1]);
                System.out.println(flipimg[8][1]);
                System.out.println(flipimg[9][1]);

                vfllipper = findViewById(R.id.image_slide);
                vfllipper.setDisplayedChild(0);
                vfllipper.setFlipInterval(200);
                vfllipper.startFlipping();
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        vfllipper.stopFlipping();
                        back.setImageResource(0);
                        text.setVisibility(View.VISIBLE);
                        text1.setVisibility(View.VISIBLE);
                        text2.setVisibility(View.VISIBLE);
                        text3.setVisibility(View.VISIBLE);
                        text4.setVisibility(View.VISIBLE);
                        text5.setVisibility(View.VISIBLE);
                        text6.setVisibility(View.VISIBLE);
                        text7.setVisibility(View.VISIBLE);
                        text8.setVisibility(View.VISIBLE);
                        text9.setVisibility(View.VISIBLE);
                        text10.setVisibility(View.VISIBLE);

                        Random ran = new Random();
                        int num = ran.nextInt(rantext.length);
                        text.setText(fliptext[last]+" "+rantext[num]);
                        System.out.println("!!!");
                        System.out.println(last);
                        task = new RouletteActivity.MyAsyncTask();
                        task.execute(fliptext[last]);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);



//                for(int i=0; i< images.length; i++ ) {
//                    System.out.println(i);
//                    System.out.println(images[i]);
//                    fllipperImages(images[i]);
//                }
            }


        });

    }
    static void shuffleArray (int[][] ar, String[] ar2) {
        Random rnd = new Random();
        for (int i = ar2.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a0 = ar[index][0];
            int a1 = ar[index][1];
            ar[index][0] = ar[i][0];
            ar[index][1] = ar[i][1];
            ar[i][0] = a0;ar[i][1] = a1;
        }
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
                    String titleFilter2 = titleFilter.replaceAll("amp;", "");
                    String title = titleFilter2.replaceAll("</b>", "");

                    Log.e("test", title);
                    Log.e("test", address);
                    Log.e("test", link);
                    System.out.println(title);
                    if(i == 0){
                        item1 = title;
                        item2 = address;
                        link1 = link;
                        System.out.println(link);
                    }else if(i==1){
                        item3 = title;
                        item4 = address;
                        link2 = link;
                    }else if(i==2){
                        item5 = title;
                        item6 = address;
                        link3 = link;
                    }else if(i==3){
                        item7 = title;
                        item8 = address;
                        link4 = link;
                    }
                    else {
                        item9 = title;
                        item10 = address;
                        link5 = link;
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
            text7.setText(item7);
            text8.setText(item8);
            text9.setText(item9);
            text10.setText(item10);

        }
    }
//    static void shuffleArray (int[] ar, String[] ar2) {
//        Random rnd = new Random();
//        for (int i = ar.length - 1; i > 0; i--) {
//            int index = rnd.nextInt(i + 1);
//            if (i == index) {
//                ++i;
//            } else {
//                int a = ar[index];
//                String a2 = ar2[index];
//
//                ar[index] = ar[i];
//                ar2[index] = ar2[i];
//
//                ar[i] = a;
//                ar2[i] = a2;
//            }
//        }
//    }
//    public void updateLayoutView(){
//        System.out.println("ran!!");
//        Random ran = new Random();
//        int num = ran.nextInt(img.length);
//        back.setBackgroundResource(img[num]);
//    }
}