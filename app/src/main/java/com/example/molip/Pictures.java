package com.example.molip;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Pictures extends Fragment {
    ImageView imageView;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup imgview = (ViewGroup) inflater.inflate(R.layout.pictures, container, false);
        imageView = (ImageView) imgview.findViewById(R.id.image);

        button = (Button) imgview.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, 1);
            }
        });
        return imgview;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            List<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipdata = data.getClipData();
            // Make sure the request was successful
            if (clipdata != null) {
                //multiple images selecetd
                for (int i = 0; i < clipdata.getItemCount(); i++) {
                    Uri imageUri = clipdata.getItemAt(i).getUri();
                    Log.d("URI", imageUri.toString());
                    try {
                        InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        bitmaps.add(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                //single image selected
                Uri imageUri = data.getData();
                Log.d("URI", imageUri.toString());
                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmaps.add(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (final Bitmap b : bitmaps) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(b);
                            }
                        });

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
//            if (resultCode == RESULT_OK) {
//                try {
//                    // 선택한 이미지에서 비트맵 생성
//                    InputStream in = getActivity().getContentResolver().openInputStream(data.getData());
//                    Bitmap img = BitmapFactory.decodeStream(in);
//                    in.close();
//                    // 이미지 표시
//                    imageView.setImageBitmap(img);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}