package com.example.molip.picturePage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.molip.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class PictureRcvAdapter extends RecyclerView.Adapter<PictureRcvAdapter.ViewHolder> {
    private ArrayList<Bitmap> imageList;
    private Context context;
    public PictureRcvAdapter(ArrayList<Bitmap> imageList, Context context) {
        System.out.println(imageList);
        this.imageList = imageList;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public int getItemCount() {
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        //TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            System.out.println("here2");
            imgProfile = (ImageView) itemView.findViewById(R.id.tab2_image);
            System.out.println(imgProfile);
//            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);

        }
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Bitmap imageData = imageList.get(position);
        System.out.println("here1");
        System.out.println(imageList);
        System.out.println(imageData);
        System.out.println(holder.imgProfile);
        if(imageList != null){
            System.out.println("here");
            holder.imgProfile.setImageBitmap(imageData);
        }else{

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageDetail.class);
                intent.putExtra("position", position);
                //intent.putExtra("image", imageData);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageData.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                ((Activity)context).startActivityForResult(intent, PictureManager.RQ_PIC_TO_DETAIL);
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
        imageList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, imageList.size());
    }
}