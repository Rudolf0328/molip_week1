package com.example.molip.randomPage;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.molip.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoneyActivity extends AppCompatActivity {
    EditText etTotal;
    TextView tvAvg;
    ImageButton btnGood, btnMid, btnBad;
    Button btnAdd, btnDelete, btnGo;
    LinearLayout llName;
    int num = 0, level = 0, total = 0, avgInt;
    double avgDouble, low = 0, high = 0;

    ArrayList<TextView> tvs = new ArrayList<>();
    ArrayList<LinearLayout> lls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        etTotal = (EditText) findViewById(R.id.money_et_price);
        tvAvg = (TextView) findViewById(R.id.money_tv_avg);
        btnGood = (ImageButton) findViewById(R.id.money_btn_good);
        btnMid = (ImageButton) findViewById(R.id.money_btn_mid);
        btnBad = (ImageButton) findViewById(R.id.money_btn_bad);
        btnAdd = (Button) findViewById(R.id.money_btn_add);
        btnDelete = (Button) findViewById(R.id.money_btn_delete);
        btnGo = (Button) findViewById(R.id.money_btn_go);
        llName = (LinearLayout) findViewById(R.id.money_ll_name);

        btnGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 0;
                btnGood.setImageResource(R.drawable.img_kind_click);
                btnMid.setImageResource(R.drawable.img_normal);
                btnBad.setImageResource(R.drawable.img_mad);
            }
        });

        btnMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 1;
                btnGood.setImageResource(R.drawable.img_kind);
                btnMid.setImageResource(R.drawable.img_normal_click);
                btnBad.setImageResource(R.drawable.img_mad);
            }
        });

        btnBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 2;
                btnGood.setImageResource(R.drawable.img_kind);
                btnMid.setImageResource(R.drawable.img_normal);
                btnBad.setImageResource(R.drawable.img_mad_click);
            }
        });

        // add button -> add person
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = new EditText(getApplicationContext());
                TextView tv = new TextView(getApplicationContext());
                LinearLayout ll = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(650, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                et.setLayoutParams(p1);
                et.setHint("이름을 입력하세요.");
                p2.setMargins(30, 0, 0, 0);
                tv.setLayoutParams(p2);
//                tv.setTextColor(Integer.parseInt("#FFFFFF"));
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setTextSize(18);
                tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
//                tv.setText("0");
                tvs.add(tv);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ll.addView(et);
                ll.addView(tv);
                et.setId(num);
                tv.setId(num);
                llName.addView(ll);
                lls.add(ll);
                num += 1;
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num -= 1;
                llName.removeAllViews();
                lls.remove(num);
                tvs.remove(num);

                for (int i = 0; i < num; i++) {
                    llName.addView(lls.get(i));
                }
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> prices = new ArrayList<>(num);
                ArrayList<Double> ratios = new ArrayList<>(num);
                String totalString = etTotal.getText().toString();

                totalString = totalString.trim();

                if (totalString.getBytes().length <= 0) {
                    Toast.makeText(MoneyActivity.this, "총 금액 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if (totalString.getBytes().length >= 10) {
                    Toast.makeText(MoneyActivity.this, "10억 미만의 금액 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if (num == 0) {
                        Toast.makeText(MoneyActivity.this, "한 명 이상 추가해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        total = Integer.parseInt(totalString);
                        avgInt = total / num;

                        avgDouble = (double) total / (double) num;

                        double ratio = 1.0 / num;
                        int test = 0;
                        System.out.println("ratio : " + ratio);
                        switch (level) {
                            case 0:
                                low = avgInt - (avgInt * 0.1);
                                high = avgInt + (avgInt * 0.1);

                                for (int i = 0; i < num; i++) {
                                    double tmp = Math.random() * (high - low) + low;
//                            ratios.add(tmp);
                                    prices.add((int) (tmp));
                                }

                                for (int i = 0; i < num; i++) {
                                    test += prices.get(i);
                                }

                                if (test > total) {
                                    int diff = test - total;
                                    System.out.println("1. " + diff);
                                    int max = Collections.max(prices);
                                    int index = prices.indexOf(max);
                                    prices.remove(index);
                                    prices.add(index, max - diff);
                                } else if (test < total) {
                                    int diff = total - test;
                                    System.out.println("2. " + diff);
                                    int min = Collections.min(prices);
                                    int index = prices.indexOf(min);
                                    prices.remove(index);
                                    prices.add(index, min + diff);
                                }

                                break;
                            case 1:
                                low = avgInt - (avgInt * 0.5);
                                high = avgInt + (avgInt * 0.5);

                                for (int i = 0; i < num; i++) {
                                    double tmp = Math.random() * (high - low) + low;
//                            ratios.add(tmp);
                                    prices.add((int) (tmp));
                                }

                                test = 0;

                                for (int i = 0; i < num; i++) {
                                    test += prices.get(i);
                                }

                                if (test > total) {
                                    int diff = test - total;
                                    System.out.println("1. " + diff);
                                    int min = Collections.min(prices);
                                    int index = prices.indexOf(min);
                                    prices.remove(index);
                                    prices.add(index, min - diff);
                                } else if (test < total) {
                                    int diff = total - test;
                                    System.out.println("2. " + diff);
                                    int max = Collections.max(prices);
                                    int index = prices.indexOf(max);
                                    prices.remove(index);
                                    prices.add(index, max + diff);
                                }
                                break;
                            case 2:
                                int tmp = (int) (Math.random() * num);
                                System.out.println(tmp);

                                for (int i = 0; i < num - 1; i++) {
                                    prices.add(0);
                                }
                                prices.add(tmp, total);
                                break;
                            default:
                                break;
                        }

                        int testtest = 0;

                        for (int i = 0; i < num; i++) {
                            testtest += prices.get(i);
                        }

                        System.out.println("adjust : " + testtest);

                        System.out.println(ratios);
                        System.out.println(prices);
                        String avgText = "Average : " + avgInt + "원";
                        tvAvg.setText(avgText);

                        for (int i = 0; i < num; i++) {
                            String priceText = prices.get(i) + "원";
                            tvs.get(i).setText(priceText);
                        }
                    }
                }
            }
        });
    }
}