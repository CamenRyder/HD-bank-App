package com.example.project_a.otherClass.Adapter;

import android.graphics.Bitmap;

public class gamePhoto {
     Bitmap photo ;

    public gamePhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
