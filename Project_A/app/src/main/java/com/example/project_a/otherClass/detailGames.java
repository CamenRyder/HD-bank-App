package com.example.project_a.otherClass;

import android.graphics.Bitmap;

public class detailGames {
    public String nameGame, option, released, publisher, price ;
    public Bitmap picture ;

    public detailGames(String nameGame, String price, String released, String publisher,String option) {
        this.nameGame = nameGame;
        this.option = option;
        this.released = released;
        this.price = price ;
        this.publisher = publisher; 
        // oh no, test source
    }
}
