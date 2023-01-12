package com.example.project_a.ViewModel;

import android.util.Log;

import com.example.project_a.API.API;
import com.example.project_a.Storage.App;
import com.example.project_a.otherClass.Games;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class m014_VM extends BaseViewModel_API{
    public static final String GET_GAMES = "GET_GAMES";
    private   List<Games> listGamee  ;
   public List<String> listGames ;
    int index = 0  ;
    public void getGameUBuy(String account)
    {
        listGamee = new ArrayList<>() ;
        index =  0  ;
        CompositeDisposable compositeDisposable = new CompositeDisposable() ;
    Retrofit rs = new Retrofit.Builder()
            .baseUrl("https://autofb18.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build() ;
    API api = rs.create(API.class) ;
        compositeDisposable.add(api.getGame(account)
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(database_getGames->
    {
                for(int i=0;i< database_getGames.games.size();i++)
                {
                    Log.e(m014_VM.class.getName(),database_getGames.games.get(i));
                    for(int j=0;j<App.getInstance().getStorage().listLocalGames.size();j++)
                    {
                        if(database_getGames.games.get(i).equals(App.getInstance().getStorage().listLocalGames.get(j).nameGame))
                        {
                            App.getInstance().getStorage().listLocalGames.get(j).isUBuy  = true ;
                        }
                    }
                }
                listGamee      =  App.getInstance().getStorage().listLocalGames ;
        Log.e(m014_VM.class.getName(),"lay duoc thong tin: " + listGamee.get(3).nameGame);
        Log.e(m014_VM.class.getName(),"lay duoc thong tin: " + listGamee.get(0).isUBuy);
        callBack.apiSuccess(GET_GAMES,listGamee);
                Log.e(m014_VM.class.getName(),"lay duoc thong tin!");
//                allInOne(listGames);

    },
    throwable ->
    {
        Log.e(m014_VM.class.getName(),"co cai cc" + throwable.getMessage() );
    }
                )
                        );


}





}


