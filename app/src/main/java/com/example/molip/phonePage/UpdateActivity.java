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
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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
    ImageView imgvProfile;
    EditText etName, etPhone, etBan;
    ImageButton imgBtnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        PhoneActivity phoneActivity = new PhoneActivity();

        imgvProfile = (ImageView) findViewById(R.id.detail_imgv_profile);
        etName = (EditText) findViewById(R.id.update_et_name);
        etPhone = (EditText) findViewById(R.id.update_et_phone);
        etBan = (EditText) findViewById(R.id.update_et_ban);
        imgBtnCheck = (ImageButton) findViewById(R.id.update_img_btn_check);

        etPhone.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);
//        System.out.println("ididididid : " + Integer.parseInt(id));
        String profile = intent.getStringExtra("profile");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String ban = intent.getStringExtra("ban");

        etName.setText(name);
        etPhone.setText(phone);
        etBan.setText(ban);

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
                String newBan = etBan.getText().toString();

                if(newPhone.equals("")) {
                    newPhone = "?";
                } else if (newBan.equals("")) {
                    newBan = "?";
                }

                if (newName.equals("")) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {

                    if (id == 9999) {
                        System.out.println("new");
//                String newProfile = imgvProfile.getImageAlpha();
                        Contact newContact = new Contact();
                        newContact.setName(newName);
                        newContact.setPhone(newPhone);
                        newContact.setBan(newBan);
                        if (newProfile == null) {
                            newContact.setProfile("null");
                        } else {
                            newContact.setProfile(newProfile.toString());
                        }
//                    Glide.with(view)
//                            .load(newProfile)
//                            .into(newContact);

                        ContactDB.getInstance(context).contactDAO().insert(newContact);
                    } else {
                        System.out.println("edit");
                        Contact contact = ContactDB.getInstance(context).contactDAO().getContact(id);
                        contact.setName(newName);
                        contact.setPhone(newPhone);
                        contact.setBan(newBan);
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

            imgvProfile.setImageURI(newProfile);

//            imgvProfile.
        }
    }
}