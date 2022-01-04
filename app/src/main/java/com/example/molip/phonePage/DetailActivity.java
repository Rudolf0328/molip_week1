package com.example.molip.phonePage;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Messenger;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.molip.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

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

    ImageView imgvProfile;
    TextView tvName, tvPhone, tvBan;
    ImageButton imgBtnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgvProfile = (ImageView) findViewById(R.id.detail_imgv_profile);
        tvName = (TextView) findViewById(R.id.detail_tv_name);
        tvPhone = (TextView) findViewById(R.id.detail_tv_phone);
        tvBan = (TextView) findViewById(R.id.detail_tv_ban);
        imgBtnEdit = (ImageButton) findViewById(R.id.update_img_btn_check);
        Intent intent = getIntent();


        int id = intent.getIntExtra("id", 0);
        String profile = intent.getStringExtra("profile");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String ban = intent.getStringExtra("ban");

        System.out.println(profile);
        try {
            imgvProfile.setClipToOutline(true);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            tvName.setText(intent.getStringExtra("name"));
            tvPhone.setText(intent.getStringExtra("phone"));
            tvBan.setText(intent.getStringExtra("ban"));

            if (profile.equals("null")) {
                int defaultProfile = R.drawable.img_default;
                imgvProfile.setImageResource(defaultProfile);
            } else {
//                Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent1.addCategory(Intent.CATEGORY_OPENABLE);
//                intent1.setType("image/*");
//
//                // Optionally, specify a URI for the file that should appear in the
//                // system file picker when it loads.
//                intent1.putExtra(DocumentsContract.EXTRA_INITIAL_URI, profile);
//
//                startActivityForResult(intent1, 3);
//                System.out.println("hehehehe" + profile);

//                TedPermission.with(getApplicationContext())
//                        .setPermissionListener(permissionListener)
//                        .setRationaleMessage("주소록 권한이 필요합니다.")
//                        .setDeniedMessage("주소록 권한을 거부하셨습니다.")
//                        .setPermissions(Intent.ACTION_OPEN_DOCUMENT)
//                        .check();

//                imgvProfile.setImageURI(Uri.parse(profile));
//                Uri uri = Uri.parse(profile);
//                getContentResolver().takePersistableUriPermission(uri, Integer.parseInt(Intent.ACTION_OPEN_DOCUMENT));
                Glide.with(this)
                        .load(profile)
                        .into(imgvProfile);
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

        imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(DetailActivity.this, UpdateActivity.class);
                edit.putExtra("id", id);
                edit.putExtra("name", name);
                edit.putExtra("phone", phone);
                edit.putExtra("profile", profile);
                edit.putExtra("ban", ban);
                startActivityForResult(edit, Manager.RC_CA_TO_UPDATE);

                // TODO : 그냥 꺼지게 했는데 안 꺼지게도 할 수 있나 intent 쓰면 되나
                finish();
            }

        });
//        return v;
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }
    };

//    @Override
//    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case General.REQUESTPERMISSION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //reload my activity with permission granted or use the features that required the permission
//
//                } else {
//                    Messenger.makeToast(getContext(), R.string.noPermissionMarshmallow);
//                }
//                break;
//        }
//    }

//        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            imgvProfile.setImageURI(data.getData());
//        }
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