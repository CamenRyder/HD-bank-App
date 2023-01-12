package com.example.project_a.ViewModel;

import android.util.Log;
import android.widget.Toast;

import com.example.project_a.API.API;
import com.example.project_a.API.Req.transferReq;
import com.example.project_a.API.Res.transferRes;
import com.example.project_a.Storage.App;

import java.util.concurrent.atomic.AtomicBoolean;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class m012_VM extends BaseViewModel_API {

    private static final String TRANSFER_GAMES = "TRANSFER_GAMES";
    public  String notify = ""  ;
    public boolean isBuy;
    public String notify2;

    public void  getGameUBuy(String gameTitle, String AccountNo,boolean isFree) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Retrofit rs = new Retrofit.Builder()
                .baseUrl("https://autofb18.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        API api = rs.create(API.class);
        compositeDisposable.add(api.save_Game(gameTitle, AccountNo)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(save_in4Res ->
                        {
                            Log.e(m012_VM.class.getName(),"Vao setProgess") ;
                            setProgress(save_in4Res.success,gameTitle,isFree) ;
                        },
                        throwable ->
                        {
                            Log.e(m012_VM.class.getName(), "something went worng!" + throwable.getMessage());


                        }
                )
        );

    }

    private void setProgress(boolean success, String gameTitle,boolean isFree)
    {
        if(success && isFree)
        {
            Log.e(m012_VM.class.getName(),"Vao case 1") ;
            App.getInstance().getStorage().notifyCase = "Mua game thanh cong!";
            Log.e(m012_VM.class.getName(), "Vao ket qua lua trog storage: "+  App.getInstance().getStorage().notifyCase);
        }else  if ( !success && isFree) {
            Log.e(m012_VM.class.getName(), "Vao case 2");
            App.getInstance().getStorage().notifyCase = "Ban da mua Game nay roi!";
            Log.e(m012_VM.class.getName(), "Vao ket qua lua trog storage: "+  App.getInstance().getStorage().notifyCase);

        }
        else  if  (success && !isFree)
        {
            Log.e(m012_VM.class.getName(),"Vao case 3") ;
            getAPI().getTransfer(new transferReq("20000","Mua game : "+ gameTitle,"002704070000059"))
                    .enqueue(initHandleResponse(TRANSFER_GAMES)); ;
            Log.e(m012_VM.class.getName(), "Vao ket qua lua trog storage: "+  App.getInstance().getStorage().notifyCase);
        }else
        {
            Log.e(m012_VM.class.getName(),"Vao case 4") ;
            App.getInstance().getStorage().notifyCase = "Ban da mua Game nay roi!";
            Log.e(m012_VM.class.getName(), "Vao ket qua lua trog storage: "+  App.getInstance().getStorage().notifyCase);
        }



    }


    @Override
    protected void handleSuccess(String key, Object body) {
        super.handleSuccess(key, body);
        Log.e(m012_VM.class.getName(),"vao handle thanh cong!") ;
        if(key.equals(TRANSFER_GAMES))
        {
            transferRes res =  (transferRes) body ;
            if(res.response.responseCode.equals("00"))
            {
                Log.e(m012_VM.class.getName(),"Mua thanh cong: handle !") ;
                App.getInstance().getStorage().notifyCase = "Mua game thanh cong!";
            }if(res.response.responseCode.equals("04"))
        {
            Log.e(m012_VM.class.getName(),"Mua  hong thanh cong: handle !") ;
            App.getInstance().getStorage().notifyCase  = "tai khoan cua ban khong du tien";
        }
        }

    }

    @Override
    protected void handleFail(String key, int code, ResponseBody errorBody)
    {
        super.handleFail(key, code, errorBody);
        Log.e(m012_VM.class.getName(),"Loi tru tien!") ;
    }
}
