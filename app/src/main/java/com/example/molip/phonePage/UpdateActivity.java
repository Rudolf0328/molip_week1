package com.example.molip.phonePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.molip.MainActivity;
import com.example.molip.R;
import com.example.molip.phonePage.data.Contact;
import com.example.molip.phonePage.data.ContactDB;
import com.google.android.material.textfield.TextInputEditText;

import java.util.zip.Inflater;

public class UpdateActivity extends AppCompatActivity {

    private ContactDB contactDB = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        PhoneActivity phoneActivity = new PhoneActivity();
        contactDB = ContactDB.getInstance(this);

        ImageView imgvProfile = (ImageView) findViewById(R.id.detail_imgv_profile);
        EditText etName = (EditText) findViewById(R.id.update_et_name);
        EditText etPhone = (EditText) findViewById(R.id.update_et_phone);
        ImageButton imgBtnCheck = (ImageButton) findViewById(R.id.update_img_btn_check);
        Intent intent = getIntent();

        String profile = intent.getStringExtra("profile");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");

        etName.setText(name);
        etPhone.setText(phone);
        System.out.println(profile);
        try {
            imgvProfile.setClipToOutline(true);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            if (profile.equals("")) {
                int defaultProfile = R.drawable.img_default;
                imgvProfile.setImageResource(defaultProfile);
            } else {
                imgvProfile.setImageURI(Uri.parse(profile));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        imgBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                contact.name = etName.getText().toString();
                contact.phone = etPhone.getText().toString();
                ContactDB.getInstance(context).contactDAO().insert(contact);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, phoneActivity).commit();
//                Intent intent = new Intent(this, MainActivity.class);
            }
        });
    }
}