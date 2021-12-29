package com.example.molip.phonePage;

import static android.app.Activity.RESULT_OK;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.PermissionChecker.checkCallingOrSelfPermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.molip.R;
import com.example.molip.phonePage.adapter.PhoneRcvAdapter;
import com.example.molip.phonePage.data.DummyData;
import com.example.molip.phonePage.data.PhoneData;

import java.lang.reflect.Array;
import java.util.ArrayList;

//외부에서 new Frag1 호출 시
public class PhoneActivity extends Fragment {
//    private FragmentHomeBinding binding;
    RecyclerView rcvPhones;
    ImageButton btnAdd;
    PhoneRcvAdapter rcvAdapter;
//    private ArrayList<PhoneData> phoneData;

//    String[] permission_list = {
//            Manifest.permission.WRITE_CONTACTS
//    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.activity_phone,container,false);
        rcvPhones = (RecyclerView) v.findViewById(R.id.phone_list);
        btnAdd = (ImageButton) v.findViewById(R.id.phone_btn_add);
        rcvPhones.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvAdapter = new PhoneRcvAdapter(DummyData.dummyList, getActivity());
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
            DummyData.dummyList.add(new PhoneData(sImage, sName, sNumber));
            rcvAdapter = new PhoneRcvAdapter(DummyData.dummyList, getActivity());
            rcvPhones.setAdapter(rcvAdapter);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

//    public void checkPermission(){
//        //현재 안드로이드 버전이 6.0미만이면 메서드를 종료한다.
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//            return;
//
//        for(String permission : permission_list){
//            //권한 허용 여부를 확인한다.
//            int chk = checkCallingOrSelfPermission(getContext(), permission);
//
//            if(chk == PackageManager.PERMISSION_DENIED){
//                //권한 허용을여부를 확인하는 창을 띄운다
//                requestPermissions(permission_list,0);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode==0)
//        {
//            for(int i=0; i<grantResults.length; i++)
//            {
//                //허용됬다면
//                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
//                }
//                else {
////                    Toast.makeText(getApplicationContext(),"앱권한설정하세요",Toast.LENGTH_LONG).show();
//////                    finish();
//                }
//            }
//        }
//    }
}