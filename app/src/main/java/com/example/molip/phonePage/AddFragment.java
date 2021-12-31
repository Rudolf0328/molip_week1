package com.example.molip.phonePage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.molip.MainActivity;
import com.example.molip.R;
import com.example.molip.phonePage.adapter.PhoneRcvAdapter;
import com.example.molip.phonePage.data.DummyData;
import com.example.molip.phonePage.data.PhoneData;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.zip.Inflater;

public class AddFragment extends Fragment {
    Context context;
    ImageView imgvProfile;
    EditText etName, etPhone;
    ImageButton imgBtnCheck;
    String name, phone, profile;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_add, container,false);

        PhoneFragment phoneFragment = new PhoneFragment();

        imgvProfile = (ImageView) v.findViewById(R.id.add_imgv_profile);
        etName = (EditText) v.findViewById(R.id.add_et_name);
        etPhone = (EditText) v.findViewById(R.id.add_et_phone);
        imgBtnCheck = (ImageButton) v.findViewById(R.id.add_img_btn_check);
//        Intent intent = getIntent();

        if (getArguments() != null)
        {
            name = getArguments().getString("name"); // 프래그먼트1에서 받아온 값 넣기
            phone = getArguments().getString("phone");
            profile = getArguments().getString("profile");
            etName.setText(name);
            etPhone.setText(phone);
            if (profile.equals("")) {
                int defaultProfile = R.drawable.img_default;
                imgvProfile.setImageResource(defaultProfile);
            } else {
                imgvProfile.setImageURI(Uri.parse(profile));
            }
        }

        imgBtnCheck.setOnClickListener(new View.OnClickListener() {
            Bundle bundle = new Bundle(); // 번들을 통해 값 전달
            @Override
            public void onClick(View view) {
                String newName = etName.getText().toString();
                String newPhone = etPhone.getText().toString();
//                Intent intent = new Intent(this, MainActivity.class);
//                DummyData.dummyList.add(new PhoneData(profile, newName, newPhone));
//                rcvAdapter = new PhoneRcvAdapter(DummyData.dummyList, getActivity());
//                rcvPhones.setAdapter(rcvAdapter);

                bundle.putString("newName", newName);//번들에 넘길 값 저장
                bundle.putString("newPhone", newPhone);
                bundle.putString("newProfile", profile);
//                PhoneActivity phoneActivity1 = new PhoneActivity();
                phoneFragment.setArguments(bundle);
//                view = view.inflate(context,0,view);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(Manager.RC_UPDATE_TO_CA, phoneFragment).commit();
            }
        });
        return v;
    }
}