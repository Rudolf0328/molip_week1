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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.molip.MainActivity;
import com.example.molip.R;
import com.example.molip.phonePage.DetailActivity;
import com.example.molip.phonePage.Manager;
import com.example.molip.phonePage.PhoneActivity;
import com.example.molip.phonePage.data.Contact;
import com.example.molip.phonePage.data.ContactDB;
import com.example.molip.phonePage.data.PhoneData;

import java.util.ArrayList;
import java.util.List;

public class PhoneRcvAdapter extends RecyclerView.Adapter<PhoneRcvAdapter.ViewHolder> {
    private List<Contact> contactList;
    private Context context;
    public PhoneRcvAdapter(List<Contact> contactList, Context context) {
        System.out.println(contactList);
        this.contactList = contactList;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public int getItemCount() {
        if (contactList == null) {
            return 0;
        }
        return contactList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        ImageButton btnCall, btnMsg;
        TextView tvName, tvPhoneNum;

        public ViewHolder(View itemView) {
            super(itemView);
            imgProfile = (ImageView) itemView.findViewById(R.id.item_imgv_profile);
            btnCall = (ImageButton) itemView.findViewById(R.id.item_btn_call);
            btnMsg = (ImageButton) itemView.findViewById(R.id.item_btn_msg);
            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);
            tvPhoneNum = (TextView) itemView.findViewById(R.id.item_tv_phone_num);
        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        //TODO : convert to getContact
        Contact contact = ContactDB.getInstance(context).contactDAO().getContact(position);
//        contactList = ContactDB.getInstance(context).contactDAO().getAll();
//        final PhoneData phoneData = contactList.get(position);
        System.out.println("pd: " + contact);
        holder.tvName.setText(contact.name);
        holder.tvPhoneNum.setText(contact.phone);
        if(contact.profile.equals("null")) {
            int resourceId = R.drawable.img_default;
            holder.imgProfile.setImageResource(resourceId);
        } else {
            holder.imgProfile.setImageURI(Uri.parse(contact.profile));
        }

        System.out.println("img: " + contact.profile);

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + contact.phone));
                context.startActivity(callIntent);
            }
        });

        holder.btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contact.phone));
                context.startActivity(msgIntent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager fm = ;
//                FragmentTransaction ft = fm.beginTransaction();
//                DetailActivity detailActivity = new DetailActivity();
//                ft.replace(Manager.RC_CA_TO_DETAIL, detailActivity);
//                ft.commit();
//                activity.get
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name", contact.name);
                intent.putExtra("phone", contact.phone);
                intent.putExtra("profile", contact.profile);
                ((Activity)context).startActivityForResult(intent, Manager.RC_CA_TO_DETAIL);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);

                dialog.setMessage("정말로 삭제하시겠습니까?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeItemView(position);
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

    private void removeItemView(int position) {
        // TODO : convert to room delete
        Contact contact = new Contact();
        contact = ContactDB.getInstance(context).contactDAO().getContact(position);
        ContactDB.getInstance(context).contactDAO().delete(contact);
//        contactList.remove(position);
        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, contactList.size());
    }

    public Bitmap loadContactPhoto(ContentResolver cr, long id, long photo_id) {
        byte[] photoBytes = null;
        Uri photoUri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, photo_id);
        Cursor c = cr.query(photoUri, new String[]{ContactsContract.CommonDataKinds.Photo.PHOTO}, null, null, null);
        try {
            if (c.moveToFirst())
                photoBytes = c.getBlob(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
        }

        if(photoBytes != null) {
            return resizingBitmap(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length));
        } else
            Log.d("PHOTO", "fail");

        return null;
    }

    public Bitmap resizingBitmap(Bitmap oBitmap) {
        if (oBitmap == null) {
            return null;
        }

        float width = oBitmap.getWidth();
        float height = oBitmap.getHeight();
        float resizing_size = 120;

        Bitmap rBitmap = null;
        if (width > resizing_size) {
            float mWidth = (float) (width / 100);
            float fScale = (float) (resizing_size / mWidth);
            width *= (fScale / 100);
            height *= (fScale / 100);
        } else if (height > resizing_size){
            float mHeight = (float) (height / 100);
            float fScale = (float) (resizing_size / mHeight);

            width *= (fScale / 100);
            height *= (fScale / 100);
        }

        rBitmap = Bitmap.createScaledBitmap(oBitmap, (int) width, (int) height, true);
        return rBitmap;
    }

}
