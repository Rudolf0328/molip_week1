package com.example.molip.picturePage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.molip.R;
import com.example.molip.picturePage.data.ImageDB;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageDetail extends AppCompatActivity {
    private PhotoView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_detail);
        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("image");
//        byte[] arr = getIntent().getByteArrayExtra("image");
//        Bitmap bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);
//        System.out.println(bitmap);
        int id = intent.getIntExtra("position", 0);
//        Bitmap bitmap = ImageDB.getInstance(this).imageDao().getContact(id).getImg();
        mImageView=(PhotoView) findViewById(R.id.imageView);
        mImageView.setImageBitmap(bitmap);
    }
}