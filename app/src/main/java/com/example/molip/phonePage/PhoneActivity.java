package com.example.molip.phonePage;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.molip.R;
import com.example.molip.phonePage.adapter.PhoneRcvAdapter;
import com.example.molip.phonePage.data.Contact;
import com.example.molip.phonePage.data.ContactDB;
import com.example.molip.phonePage.data.DummyData;
import com.example.molip.phonePage.data.PhoneData;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

//외부에서 new Frag1 호출 시
public class PhoneActivity extends Fragment {
    //    private FragmentHomeBinding binding;
    RecyclerView rcvPhones;
    Button btnAdd, btnNew;
    PhoneRcvAdapter rcvAdapter;
    ArrayList<Contact> contactList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.activity_phone, container, false);
        try {
            String newName = getArguments().getString("newName");
            String newPhone = getArguments().getString("newPhone");
            String newProfile = getArguments().getString("newProfile");

            DummyData.dummyList.add(new PhoneData(newProfile, newName, newPhone));
        } catch (Exception e) {
            System.out.println(e);
        }
        rcvPhones = (RecyclerView) v.findViewById(R.id.phone_list);
        btnAdd = (Button) v.findViewById(R.id.phone_btn_add);
        btnNew = (Button) v.findViewById(R.id.phone_btn_new);
        rcvPhones.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvAdapter = new PhoneRcvAdapter();
        rcvPhones.setAdapter(rcvAdapter);
//        checkPermission();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TedPermission.with(getContext())
                        .setPermissionListener(permissionListener)
                        .setRationaleMessage("주소록 권한이 필요합니다.")
                        .setDeniedMessage("주소록 권한을 거부하셨습니다.")
                        .setPermissions(Manifest.permission.READ_CONTACTS)
                        .check();
                Intent add = new Intent(Intent.ACTION_PICK);
                add.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(add, 0);
            }

        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                intent.putExtra("id", 9999);
//                intent.putExtra("id", "null");
//                add.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 0);
            }

        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Contact> contacts = ContactDB.getInstance(getContext()).contactDAO().getAll();
        rcvAdapter.submitList(contacts);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String sName = "error";
        String sNumber = "error";
        String sImage = "error";

        Contact newContact = new Contact();
//        String sPerson = "error";

        if (resultCode == RESULT_OK) {
            Cursor cursor = getActivity().getContentResolver().query(data.getData(), new String[]{ContactsContract.CommonDataKinds.Phone.PHOTO_URI, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();
            sImage = cursor.getString(0);
            sName = cursor.getString(1);
            sNumber = cursor.getString(2);
            cursor.close();
        }
        if (!sName.equals("error") && !sNumber.equals("error")) {
            if (sImage == null) {
                sImage = "null";
            }
            System.out.println("image: " + sImage);

            newContact.setName(sName);
            newContact.setPhone(sNumber);
            newContact.setProfile(sImage);
            newContact.setBan("?");

            ContactDB.getInstance(getActivity()).contactDAO().insert(newContact);
            contactList = (ArrayList<Contact>) ContactDB.getInstance(getActivity()).contactDAO().getAll();
            System.out.println("f : " + contactList.toString());
            rcvAdapter.submitList(contactList);

//            System.out.println("d : " + contactList);
//            DummyData.dummyList.add(new PhoneData(sImage, sName, sNumber));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getContext(), "권한이 거부됨", Toast.LENGTH_SHORT).show();
        }
    };
}