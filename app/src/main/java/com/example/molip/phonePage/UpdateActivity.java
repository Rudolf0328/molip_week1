package com.example.molip.phonePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.molip.R;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ImageView imgvProfile = (ImageView) findViewById(R.id.detail_imgv_profile);
        TextInputEditText tietName = (TextInputEditText) findViewById(R.id.update_til_name);
        TextInputEditText tietPhone = (TextInputEditText) findViewById(R.id.update_til_phone);
        ImageButton imgBtnCheck = (ImageButton) findViewById(R.id.update_img_btn_check);
        Intent intent = getIntent();

        String profile = intent.getStringExtra("profile");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        System.out.println(profile);
        try {
            imgvProfile.setClipToOutline(true);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            tvName.setText(intent.getStringExtra("name"));
            tvPhone.setText(intent.getStringExtra("phone"));

            if (profile.equals("null")) {
                int defaultProfile = R.drawable.img_default;
                imgvProfile.setImageResource(defaultProfile);
            } else {
                imgvProfile.setImageURI(Uri.parse(profile));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}