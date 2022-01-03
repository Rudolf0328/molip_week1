package com.example.molip.phonePage;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import com.example.molip.picturePage.PictureRcvAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.Inflater;

public class UpdateActivity extends AppCompatActivity {
    Context context;
    Uri newProfile;

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

        int id = intent.getIntExtra("id", 0);
//        System.out.println("ididididid : " + Integer.parseInt(id));
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

        imgvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
//                intent1.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
                startActivityForResult(intent1, 1);
//                imgvProfile.setImageURI(newProfile);
            }
        });

        imgBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = etName.getText().toString();
                String newPhone = etPhone.getText().toString();

                if(id == 9999) {
                    System.out.println("new");
//                String newProfile = imgvProfile.getImageAlpha();
                    Contact newContact = new Contact();
                    newContact.setName(newName);
                    newContact.setPhone(newPhone);
//                    newContact.setProfile(newProfile.toString());

                    ContactDB.getInstance(context).contactDAO().insert(newContact);
                } else {
                    System.out.println("edit");
                    Contact contact = ContactDB.getInstance(context).contactDAO().getContact(id);
                    contact.setName(newName);
                    contact.setPhone(newPhone);
//                    contact.setProfile(newProfile.toString());
                    System.out.println(contact.toString());

                    ContactDB.getInstance(context).contactDAO().update(contact);
//                    contact.setProfile(newProfile);
                }
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            //single image selected
            Uri imageUri = data.getData();
            Log.d("URI", imageUri.toString());
            newProfile = imageUri;

//            imgvProfile.
        }
    }
}