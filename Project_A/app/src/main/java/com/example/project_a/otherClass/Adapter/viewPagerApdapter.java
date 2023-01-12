package com.example.project_a.otherClass.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.project_a.R;

import java.util.List;

public class viewPagerApdapter extends PagerAdapter {
    Context context ;
    private List<gamePhoto> listGamePhoto ;
    private gamePhoto data ;
    private ImageView imageView ;
    @Override
    public int getCount() {
        return listGamePhoto.size();
    }

    public viewPagerApdapter(Context context, List<gamePhoto> listGamePhoto) {
        this.context = context;
        this.listGamePhoto = listGamePhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup Viewpaper, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_gamephoto,Viewpaper,false) ;
        imageView = view.findViewById(R.id.iv_imagePaper) ;
        data = listGamePhoto.get(position);
        imageView.setImageBitmap(data.photo);
        Viewpaper.addView(view);
        return view ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
