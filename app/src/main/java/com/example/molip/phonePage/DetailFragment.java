package com.example.molip.phonePage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.molip.R;

import java.util.Objects;

public class DetailFragment extends Fragment {
    ImageView imgvProfile;
    TextView tvName, tvPhone;
    ImageButton imgBtnEdit;
    String name, phone, profile;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_detail, container, false);

        imgvProfile = (ImageView) v.findViewById(R.id.add_imgv_profile);
        tvName = (TextView) v.findViewById(R.id.detail_tv_name);
        tvPhone = (TextView) v.findViewById(R.id.detail_tv_phone);
        imgBtnEdit = (ImageButton) v.findViewById(R.id.add_img_btn_check);
//        Intent intent = getIntent();

        // TODO : convert intent to getArguments
        if (getArguments() != null)
        {
            name = getArguments().getString("name"); // 프래그먼트1에서 받아온 값 넣기
            phone = getArguments().getString("phone");
            profile = getArguments().getString("profile");
            tvName.setText(name);
            tvPhone.setText(phone);
            if (profile.equals("null")) {
                int defaultProfile = R.drawable.img_default;
                imgvProfile.setImageResource(defaultProfile);
            } else {
                imgvProfile.setImageURI(Uri.parse(profile));
            }
        }

        imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            AddFragment addFragment = new AddFragment();
            Bundle bundle = new Bundle();
            @Override
            public void onClick(View view) {
                bundle.putString("name", name);
                bundle.putString("phone", phone);
                bundle.putString("profile", profile);
                addFragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(Manager.RC_CA_TO_UPDATE, addFragment).commit();
            }

        });
        return v;
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