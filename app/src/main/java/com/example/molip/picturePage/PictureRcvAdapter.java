package com.example.molip.picturePage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.molip.R;
import com.example.molip.picturePage.data.Image;
import com.example.molip.picturePage.data.ImageDB;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PictureRcvAdapter extends RecyclerView.Adapter<PictureRcvAdapter.ViewHolder> {
    private List<Image> imageList;
//    private Context context = new;
    public PictureRcvAdapter() {
    }

    public void submitList(List<Image> list) {
        imageList = list;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public int getItemCount() {
        if(imageList == null) {
            return 0;
        }
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        //TextView tvName;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            System.out.println("here2");
            imgProfile = (ImageView) itemView.findViewById(R.id.tab2_image);
            System.out.println(imgProfile);
            context = itemView.getContext();
//            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);

        }

        void onBind(Image image, final int position) {
            System.out.println(position);
            System.out.println(imageList);
//            final Bitmap imageData = imageList.get(position);
//            System.out.println("here1");
//            System.out.println(imageList);
//            System.out.println(imageData);
//            System.out.println(holder.imgProfile);
            if(imageList != null){
                System.out.println("hereherehere");

                //new
                Bitmap temp = image.getImg();
                ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
                temp.compress( Bitmap.CompressFormat.JPEG, 30, stream) ;
                byte[] byteArray = stream.toByteArray() ;
//                Bitmap rebitmap = BitmapFactory.decodeByteArray( byteArray, 0, byteArray.length ) ;
                Glide.with(itemView).load(byteArray).into(imgProfile);
                imgProfile.setImageBitmap(image.getImg());
            }else{

            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int id = ImageDB.getInstance(context).imageDao().getContact()
//                    int id = imageList.get(position).imageId;
                    Intent intent2 = new Intent(context, ImageDetail.class);
                    intent2.putExtra("position", position);

                    //??????
//                    intent2.putExtra("image", image.getImg());
                    Bitmap bitmap = image.getImg();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent2.putExtra("image",byteArray);
                    System.out.println("index : " + position);
                    ((Activity)context).startActivityForResult(intent2, PictureManager.RQ_PIC_TO_DETAIL);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);

                    dialog.setMessage("????????? ?????????????????????????");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Image image = ImageDB.getInstance(context).imageDao().getContact(position);
                            ImageDB.getInstance(context).imageDao().delete(image);
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
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        System.out.println("onbindviewholder");
//        Bitmap bitmap = StringToBitmap();
        holder.onBind(imageList.get(position), position);
    }

    private void removeItemView(int position) {
        imageList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, imageList.size());
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

}