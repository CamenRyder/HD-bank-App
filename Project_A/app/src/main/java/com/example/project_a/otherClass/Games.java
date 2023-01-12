package com.example.project_a.otherClass;

import android.graphics.Bitmap;

public class Games {
    public String nameGame, option, released, publisher, price ;
    public Bitmap picture ;
    public boolean isUBuy ;

    public Games(String nameGame, String option, String released, String publisher, String price, Bitmap picture) {
        this.nameGame = nameGame;
        this.option = option;
        this.released = released;
        this.publisher = publisher;
        this.price = price;
        this.picture = picture;
        this.isUBuy = false ;
    }
}
