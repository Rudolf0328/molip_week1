package com.example.molip.phonePage;

import static android.app.Activity.RESULT_OK;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.molip.R;
import com.example.molip.phonePage.adapter.PhoneRcvAdapter;
import com.example.molip.phonePage.data.DummyData;
import com.example.molip.phonePage.data.PhoneData;

public class DetailActivity extends AppCompatActivity {

//        final int position = 0;
//        ViewGroup v = (ViewGroup) inflate(R.layout.activity_detail,container,false);

    //    private FragmentHomeBinding binding;
//    RecyclerView rcvPhones;
//    ImageButton btnAdd;
//    PhoneRcvAdapter rcvAdapter;
//    Context context = this;
//    private Context context;
//
//    public DetailActivity(Context context) {
//        this.context = context;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imgvProfile = (ImageView) findViewById(R.id.detail_imgv_profile);
        TextView tvName = (TextView) findViewById(R.id.detail_tv_name), tvPhone = (TextView) findViewById(R.id.detail_tv_phone);
        Intent intent = getIntent();

        String profile = intent.getStringExtra("profile");
//        String name = intent.getStringExtra("name");
//        String phone = intent.getStringExtra("phone");
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
//        rcvPhones = (RecyclerView) v.findViewById(R.id.phone_list);
//        btnAdd = (ImageButton) v.findViewById(R.id.phone_btn_add);
//        rcvPhones.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rcvAdapter = new PhoneRcvAdapter(DummyData.dummyList, getActivity());
//        rcvPhones.setAdapter(rcvAdapter);
//        checkPermission();
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent add = new Intent(Intent.ACTION_PICK);
//                add.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
//                startActivityForResult(add, 0);
//            }
//
//        });
//        return v;
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        String sName = "error";
//        String sNumber = "error";
//        String sImage = "error";
////        String sPerson = "error";
//
//        if (resultCode == RESULT_OK) {
//            Cursor cursor = getActivity().getContentResolver().query(data.getData(), new String[]{ContactsContract.CommonDataKinds.Phone.PHOTO_URI, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
//            cursor.moveToFirst();
//            sImage = cursor.getString(0);
//            sName = cursor.getString(1);
//            sNumber = cursor.getString(2);
//            cursor.close();
//        }
//        if (!sName.equals("error") && !sNumber.equals("error")) {
//            if (sImage == null) {
//                sImage = "null";
//            }
//            System.out.println("image: " + sImage);
//            DummyData.dummyList.add(new PhoneData(sImage, sName, sNumber));
//            rcvAdapter = new PhoneRcvAdapter(DummyData.dummyList, getActivity());
//            rcvPhones.setAdapter(rcvAdapter);
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}