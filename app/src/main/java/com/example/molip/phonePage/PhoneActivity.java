package com.example.molip.phonePage;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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

import java.util.List;
import java.util.Objects;

//외부에서 new Frag1 호출 시
public class PhoneActivity extends Fragment {
//    private FragmentHomeBinding binding;
    RecyclerView rcvPhones;
    ImageButton btnAdd, btnNew;
    PhoneRcvAdapter rcvAdapter;
    ContactDB contactDb = null;
    List<Contact> contactList;
    List<Contact> newContactList;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.activity_phone,container,false);
        rcvPhones = (RecyclerView) v.findViewById(R.id.phone_list);
        btnAdd = (ImageButton) v.findViewById(R.id.phone_btn_add);
        btnNew = (ImageButton) v.findViewById(R.id.phone_btn_new);
        rcvPhones.setLayoutManager(new LinearLayoutManager(getActivity()));
        //TODO : convert dummy to ROOM
        contactDb = ContactDB.getInstance(getActivity());
        class InsertRunnable implements Runnable {
            @Override
            public void run() {

                contactList =ContactDB.getInstance(context).contactDAO().getAll();
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();

        rcvAdapter = new PhoneRcvAdapter(contactList, getActivity());
        rcvPhones.setAdapter(rcvAdapter);
//        checkPermission();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(Intent.ACTION_PICK);
                add.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(add, 0);
            }

        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNew = new Intent(getActivity(), UpdateActivity.class);
                addNew.putExtra("name", "");
                addNew.putExtra("phone", "");
                addNew.putExtra("profile", "");
                Objects.requireNonNull(getActivity()).startActivityForResult(addNew, Manager.RC_CA_TO_UPDATE);
            }
        });
        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String sName = "error";
        String sNumber = "error";
        String sImage = "error";
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
            //TODO : convert dummy to ROOM
            class InsertRunnable implements Runnable {
                @Override
                public void run() {
                    Contact contact = new Contact();
                    contact.name = sName;
                    contact.phone = sNumber;
                    contact.profile = sImage;


                    ContactDB.getInstance(context).contactDAO().insert(contact);
                    newContactList = ContactDB.getInstance(context).contactDAO().getAll();
//            DummyData.dummyList.add(new PhoneData(sImage, sName, sNumber));
                    rcvAdapter = new PhoneRcvAdapter(newContactList, getActivity());
                    rcvPhones.setAdapter(rcvAdapter);
                }
            }
            InsertRunnable insertRunnable = new InsertRunnable();
            Thread t = new Thread(insertRunnable);
            t.start();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onDestroy() {
        super.onDestroy();
        ContactDB.destroyInstance();
    }
}
