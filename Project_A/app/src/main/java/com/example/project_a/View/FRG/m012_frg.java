package com.example.project_a.View.FRG;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project_a.API.API;
import com.example.project_a.API.Req.transferReq;
import com.example.project_a.API.Res.transferRes;
import com.example.project_a.R;
import com.example.project_a.Storage.App;
import com.example.project_a.ViewModel.m012_VM;
import com.example.project_a.databinding.M012SearchingBinding;
import com.example.project_a.otherClass.Adapter.gamePhoto;
import com.example.project_a.otherClass.Adapter.viewPagerApdapter;
import com.example.project_a.otherClass.Games;
import com.example.project_a.otherClass.detailGames;
import com.example.project_a.otherClass.papers;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class m012_frg extends baseFRG<M012SearchingBinding,m012_VM> {
    private List<detailGames> list ;
    private int index = 0 ;
    private List<gamePhoto> gamePhotoList  ;
    private ImageView dialogPic;
    private String price;
    private String nameGame;

    @Override
    protected void initViews() {
                App.getInstance().getStorage().initStorage();
                importList();
        importListPaper() ;

    }

    private void importListPaper()
    {
        AssetManager asst = context.getAssets();
        binding.lnListFind.removeAllViews();
        try {
            String[] paper = asst.list("GameBackUp/photo/") ;
            for(String a : paper)
            {
                InputStream in = asst.open("GameBackUp/photo/" + a) ;
                Bitmap bitmap = BitmapFactory.decodeStream(in) ;
                View itemview = LayoutInflater.from(context).inflate(R.layout.item_detailgame,null) ;
                ImageView iv_games = itemview.findViewById(R.id.iv_imageGames) ;
                iv_games.setImageBitmap(bitmap);
                list.get(index).picture = bitmap ;
                itemview.setTag(index);
                detailGames item = list.get(index++);
                TextView nameGame = itemview.findViewById(R.id.tv_titleGame) ;
                TextView tv_price = itemview.findViewById(R.id.tv_price) ;
                TextView tv_publisher = itemview.findViewById(R.id.tv_homeSource) ;
                TextView released = itemview.findViewById(R.id.tv_release) ;
                TextView option = itemview.findViewById(R.id.tv_option) ;
                nameGame.setText(item.nameGame);
                tv_price.setText(item.price);
                tv_publisher.setText(item.publisher);
                released.setText(item.released);
                option.setText(item.option);
                Games game = new Games(item.nameGame,item.option,item.released,item.publisher,item.price,bitmap);
                App.getInstance().getStorage().listLocalGames.add(App.getInstance().getStorage().index++,game);
                binding.lnListFind.addView(itemview);
                itemview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                        progressBuyGame(v);

                    }
                });
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void progressBuyGame(View v)
    {
        getListPhotoGames(v);
        viewPagerApdapter adtapter = new viewPagerApdapter(context,gamePhotoList);
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_buyying);
        Window window = dialog.getWindow() ;
        if(window == null) return ;
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes() ;
        windowAttributes.gravity = Gravity.CENTER;

        window.setAttributes(windowAttributes);
        dialog.setCancelable(true);
        TextView tv_title , tv_Option , tv_Publisher, tv_price;
        tv_title = dialog.findViewById(R.id.tv_dialogTitle) ;
        tv_Option = dialog.findViewById(R.id.tv_dialogOption) ;
        tv_Publisher = dialog.findViewById(R.id.tv_dialogPulisher) ;
        tv_price = dialog.findViewById(R.id.tv_dialogPrice) ;
        tv_Option.setText(list.get(((int)v.getTag())).option);
        tv_title.setText(list.get(((int)v.getTag())).nameGame);
        nameGame =  list.get(((int)v.getTag())).nameGame ;
        tv_Publisher.setText(list.get(((int)v.getTag())).publisher);
            tv_price.setText(list.get(((int)v.getTag())).price);
         price = list.get(((int) v.getTag())).price;
         dialogPic = dialog.findViewById(R.id.dialog_picture) ;
        dialogPic.setImageBitmap(list.get((int)v.getTag()).picture);
        ViewPager showPic = dialog.findViewById(R.id.vp_listPhotoGame) ;
        showPic.setAdapter(adtapter);
        dialog.show();


            tv_price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                              if(  tv_price.getText().toString().equals("Miễn phí"))
                              {
//                                  Intent intent = new Intent(Intent.ACTION_VIEW);
//                                  intent.setData(Uri.parse("https://game4v.com/")) ;
//                                  startActivity(intent);
                                 startActivity(new Intent().setData(Uri.parse("https://play.google.com/store/apps")));
                                 viewModel.getGameUBuy(nameGame,App.getInstance().getStorage().accountNo,true) ;
                                  Toast.makeText(context, App.getInstance().getStorage().notifyCase, Toast.LENGTH_SHORT).show();

                              }else
                              {

                                  viewModel.getGameUBuy(nameGame,App.getInstance().getStorage().accountNo,false) ;
//                                      viewModel.BuyGames("20000đ","Tien mua game: "+ nameGame,"002704070000059");
                                  Toast.makeText(context, App.getInstance().getStorage().notifyCase, Toast.LENGTH_SHORT).show();

                              }
                }
            });





        }




    private void getListPhotoGames(View v) {
        int index =(int) v.getTag() + 1 ;
        String a = String.valueOf(index) ;
        String path = "viewPaper/Game/game"+a+"/" ;
        Log.e(m012_frg.class.getName(),"Duong dan: "+ path) ;
        AssetManager asst = context.getAssets();
        gamePhotoList = new ArrayList<>() ;
        try{
            String[] nameList =  asst.list(path) ;
            for (String names : nameList
                 ) {
                InputStream in = asst.open(path + names) ;
                Bitmap bitmap = BitmapFactory.decodeStream(in) ;
                gamePhoto gamePhotos = new gamePhoto(bitmap) ;
                gamePhotoList.add(gamePhotos);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void importList() {
        AssetManager ast = context.getAssets();
        list = new ArrayList<>()  ;
        try{
            InputStream in = ast.open("GameBackUp/listGame.txt") ;
            InputStreamReader inSR =new InputStreamReader(in, StandardCharsets.UTF_8) ;
            BufferedReader reader = new BufferedReader(inSR) ;

            String nameGame = null ;
            String  option = null, released = null, publisher = null, price = null  ;
            String line = reader.readLine() ;
            while(line != null)
            {
                if(nameGame==null)
                    nameGame = line ;
                else if(line.contains("','0');"))
                {
                    detailGames game  = new detailGames(nameGame,price,released,publisher,option) ;
                    list.add(game) ;
                    nameGame = null ;
                }else if(!line.isEmpty())
                {
                    price = line ;
                    line = reader.readLine() ;
                    option = line ;
                    line = reader.readLine() ;
                    publisher = line ;
                    line = reader.readLine() ;
                    released = line ;
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

    @Override
    protected Class<m012_VM> ClassVM() {
        return m012_VM.class;
    }

    @Override
    protected M012SearchingBinding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M012SearchingBinding.inflate(inflater,container,false);
    }
}
