package com.example.molip.randomPage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.molip.R;

import java.util.Random;

public class RouletteActivity extends AppCompatActivity{
    private SwipeRefreshLayout swipeRefreshLayout;
    ImageView back;
    ImageView ranimg;
    TextView text;
    int[] img = {R.drawable.camera, R.drawable.gallery, R.drawable.img_contact, R.drawable.girl, R.drawable.boy};
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
                int[][] flipimg = new int[][] {{R.drawable.chicken,1}, {R.drawable.pizza,2}, {R.drawable.ddokk,3}, {R.drawable.rice,4}, {R.drawable.hamburger,5}, {R.drawable.sandwich,6}, {R.drawable.sushi,7}, {R.drawable.steak,8}, {R.drawable.pasta,9}, {R.drawable.bread,10}};
                String[] fliptext = {"치킨","피자","떡볶이","백반","햄버거","샌드위치","초밥","스테이크","파스타","빵"};
                String[] rantext = {"먹어.","어때?","먹자.","먹을까?","고고!"};
                ranimg = (ImageView) findViewById(R.id.ranimage);
                ranimg.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);
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
                        Random ran = new Random();
                        int num = ran.nextInt(rantext.length);
                        text.setText(fliptext[last]+" "+rantext[num]);
                        System.out.println("!!!");
                        System.out.println(last);
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