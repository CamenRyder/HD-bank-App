package com.example.project_a.View.FRG;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_a.R;
import com.example.project_a.View.FRG.baseFRG;
import com.example.project_a.ViewModel.m011_VM;
import com.example.project_a.databinding.M011ShowpagerBinding;
import com.example.project_a.otherClass.papers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class m011_frg extends baseFRG<M011ShowpagerBinding, m011_VM> {
    private List<papers> list ;
    private int index = 0  ;
    @Override
    protected void initViews() {
            importList();
        importListPaper() ;
    }
    private void importList() {
        AssetManager ast = context.getAssets();
        list = new ArrayList<>()  ;
        try{
            InputStream in = ast.open("NewPaper/data.txt") ;
            InputStreamReader inSR =new InputStreamReader(in, StandardCharsets.UTF_8) ;
            BufferedReader reader = new BufferedReader(inSR) ;

            String name = null ;
            String  content = null, date = null ;
            String line = reader.readLine() ;
            while(line != null)
            {
                if(name==null)
                    name = line ;
                else if(line.contains("','0');"))
                {
                    papers paper = new papers(name,content,date) ;
                    list.add(paper) ;
                    name = null ;
                }else if(!line.isEmpty())
                {
                    content = line ;
                    line = reader.readLine() ;
                    date = line ;
                } line = reader.readLine() ;
            }
            in.close();
            inSR.close();
            reader.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void importListPaper() {
        AssetManager asst = context.getAssets();
        binding.lnItem.removeAllViews();
        try {
            String[] paper = asst.list("NewPaper/photo/") ;
            for(String a : paper)
            {
                InputStream in = asst.open("NewPaper/photo/" + a) ;
                Bitmap bitmap = BitmapFactory.decodeStream(in) ;
                View itemview = LayoutInflater.from(context).inflate(R.layout.item_newpaper,null) ;
                ImageView iv_resouce = itemview.findViewById(R.id.iv_newpaper) ;
                iv_resouce.setImageBitmap(bitmap);
                papers item = list.get(index++);
                TextView title = itemview.findViewById(R.id.tv_title) ;
                TextView content = itemview.findViewById(R.id.tv_content) ;
                TextView date = itemview.findViewById(R.id.tv_date) ;
                title.setText(item.title);
                content.setText(item.content);
                date.setText(item.date);
                binding.lnItem.addView(itemview);
                itemview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://game4v.com/")) ;
                        startActivity(intent);
                    }
                });
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<m011_VM> ClassVM() {
        return m011_VM.class;
    }

    @Override
    protected M011ShowpagerBinding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M011ShowpagerBinding.inflate(inflater,container,false);
    }
}
