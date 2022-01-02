package com.example.molip.phonePage;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.molip.MainActivity;
import com.example.molip.R;
import com.example.molip.phonePage.adapter.PhoneRcvAdapter;
import com.example.molip.phonePage.data.Contact;
import com.example.molip.phonePage.data.ContactDB;
import com.example.molip.phonePage.data.DummyData;
import com.example.molip.phonePage.data.PhoneData;
import com.google.android.material.textfield.TextInputEditText;

import java.util.zip.Inflater;

public class UpdateActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        PhoneActivity phoneActivity = new PhoneActivity();

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

//            tvName.setText(intent.getStringExtra("name"));
//            tvPhone.setText(intent.getStringExtra("phone"));

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
                Contact newContact = new Contact();
                String newName = etName.getText().toString();
                String newPhone = etPhone.getText().toString();

                newContact.name = newName;
                newContact.phone = newPhone;

                ContactDB.getInstance(context).contactDAO().insert(newContact);
//                Intent intent = new Intent(this, MainActivity.class);
//                DummyData.dummyList.add(new PhoneData(profile, newName, newPhone));
//                rcvAdapter = new PhoneRcvAdapter(DummyData.dummyList, getActivity());
//                rcvPhones.setAdapter(rcvAdapter);
//                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
//                bundle.putString("newName", newName);//번들에 넘길 값 저장
//                bundle.putString("newPhone", newPhone);
//                bundle.putString("newProfile", profile);
////                PhoneActivity phoneActivity1 = new PhoneActivity();
//                phoneActivity.setArguments(bundle);
//                view = view.inflate(context,0,view);

//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.view_pager, phoneActivity).commit();
                finish();
            }
        });
    }
}