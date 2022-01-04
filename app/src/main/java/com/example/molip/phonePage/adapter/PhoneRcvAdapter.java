package com.example.molip.phonePage.adapter;

import static androidx.core.app.ActivityCompat.requestPermissions;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.molip.MainActivity;
import com.example.molip.R;
import com.example.molip.phonePage.DetailActivity;
import com.example.molip.phonePage.Manager;
import com.example.molip.phonePage.PhoneActivity;
import com.example.molip.phonePage.data.Contact;
import com.example.molip.phonePage.data.ContactDB;
import com.example.molip.phonePage.data.PhoneData;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class PhoneRcvAdapter extends RecyclerView.Adapter<PhoneRcvAdapter.ViewHolder> {
    private List<Contact> phoneList;

    public void submitList(List<Contact> list) {
        phoneList = list;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public int getItemCount() {
        if (phoneList == null) {
            return 0;
        }
        return phoneList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        ImageButton btnCall, btnMsg;
        TextView tvName, tvPhoneNum;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            imgProfile = (ImageView) itemView.findViewById(R.id.item_imgv_profile);
            btnCall = (ImageButton) itemView.findViewById(R.id.item_btn_call);
            btnMsg = (ImageButton) itemView.findViewById(R.id.item_btn_msg);
            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);
            tvPhoneNum = (TextView) itemView.findViewById(R.id.item_tv_phone_num);
            context = itemView.getContext();
        }

        void onBind(Contact contact) {
            tvName.setText(contact.getName());
            tvPhoneNum.setText(contact.getPhone());

            //TODO: 글라이드
            if(contact.getProfile() == null || contact.getProfile().equals("null")) {
                int resourceId = R.drawable.img_default;
                Glide.with(itemView)
                        .load(resourceId)
                        .into(imgProfile);
//                imgProfile.setImageResource(resourceId);
            } else {
//                Uri p = Uri.parse(contact.getProfile());
//                context.getContentResolver().takePersistableUriPermission(p, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                Glide.with(itemView)
                        .load(contact.getProfile())
                        .into(imgProfile);
//                System.out.println(p);

//                context.getContentResolver().takePersistableUriPermission(p, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                imgProfile.setImageURI(contact.getProfile());
            }

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + contact.getPhone()));
                    context.startActivity(callIntent);
                }
            });

            btnMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contact.getPhone()));
                    context.startActivity(msgIntent);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                FragmentManager fm = ;
//                FragmentTransaction ft = fm.beginTransaction();
//                DetailActivity detailActivity = new DetailActivity();
//                ft.replace(Manager.RC_CA_TO_DETAIL, detailActivity);
//                ft.commit();
//                activity.get
                    Intent intent = new Intent(context, DetailActivity.class);
                    System.out.println("idid : " + contact.contactId);
                    intent.putExtra("id", contact.contactId);
                    intent.putExtra("name", contact.getName());
                    intent.putExtra("phone", contact.getPhone());
                    intent.putExtra("profile", contact.getProfile());
                    intent.putExtra("ban", contact.getBan());
//                    intent.putExtra("position", position);
//                    intent.putExtra("name", phoneData.getName());
//                    intent.putExtra("phone", phoneData.getPhoneNum());
//                    intent.putExtra("profile", phoneData.getProfileRes());
                    ((Activity)context).startActivityForResult(intent, Manager.RC_CA_TO_DETAIL);
//                    notifyDataSetChanged();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);

                    dialog.setMessage("정말로 삭제하시겠습니까?");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            phoneList.remove(contact);
                            ContactDB.getInstance(context).contactDAO().delete(contact);
                            notifyDataSetChanged();
                        }
                    });

                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    dialog.show();
                    return false;
                }
            });
        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBind(phoneList.get(position));



//        System.out.println("img: " + phoneData.getProfileRes());


    }

    private void removeItemView(int position) {
        phoneList.remove(position);
        notifyDataSetChanged();
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, phoneList.size());
    }

//    PermissionListener permissionListener = new PermissionListener() {
//        @Override
//        public void onPermissionGranted() {
////            Toast.makeText(, "권한이 허용됨", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
////            Toast.makeText(getApplicationContext(), "권한이 거부됨", Toast.LENGTH_SHORT).show();
//        }
//    };

}
