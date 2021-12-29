package com.example.molip.data;

public class ImageData {
    private String image, imgname, imgtime;
    public ImageData(){}
    public ImageData(String image,String imgname,String imgtime) {
        this.image = image;
        this.imgname = imgname;
        this.imgtime = imgtime;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getImgname() {
        return imgname;
    }
    public void setImgname(String imgname) {
        this.imgname = imgname;
    }
    public String getImgtime() {
        return imgtime;
    }
    public void setImgtime(String imgtime) {
        this.imgtime = imgtime;
    }
}
