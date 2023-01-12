package com.example.project_a.API;

import android.util.Log;

import com.example.project_a.API.Req.BalancReq;
import com.example.project_a.API.Req.LoginReq;
import com.example.project_a.API.Req.Register;
import com.example.project_a.API.Req.TranhisReq;
import com.example.project_a.API.Req.resetPwReq;
import com.example.project_a.API.Req.transferReq;
import com.example.project_a.API.Res.BalanceRes;
import com.example.project_a.API.Res.GetKey;
import com.example.project_a.API.Res.LoginRes;
import com.example.project_a.API.Res.RegisterRes;
import com.example.project_a.API.Res.TranhisRes;
import com.example.project_a.API.Res.database_getGames;
import com.example.project_a.API.Res.database_getIn4;
import com.example.project_a.API.Res.database_postGameUBuy;
import com.example.project_a.API.Res.resetPwRes;
import com.example.project_a.API.Res.database_postIn4;
import com.example.project_a.API.Res.transferRes;
import com.example.project_a.Storage.App;
import com.example.project_a.ViewModel.m003_VM;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API
{
    String key = "eyJraWQiOiJXcDRGMndiQVpMa1d2WWgyNDhnYjNtUHBLRzZTdDRNcG85Tmc3U2diZ2E0PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI4MWViNDQ4ZS00OWIzLTQzMGQtYWJkOS03ZDMyNWM0ZTkxYmYiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmFwLXNvdXRoZWFzdC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoZWFzdC0xX1FiMVE4VFBzVSIsImNvZ25pdG86dXNlcm5hbWUiOiI4MWViNDQ4ZS00OWIzLTQzMGQtYWJkOS03ZDMyNWM0ZTkxYmYiLCJvcmlnaW5fanRpIjoiMzNiZTY1YWItNDAwMy00YzkwLWE0MGEtNDBmNDNkZTg5ZjYwIiwiYXVkIjoic2lrY25laTR0MmgzbnRrcWo1ZDQ5bHR2ciIsImV2ZW50X2lkIjoiYjRhZWI5OTUtYTNiNi00MDhlLTk4MTctM2UxMTdmNGQ1ZDBkIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE2NzEyMTMwODIsIm5hbWUiOiJNaW5oIEhp4bq_dSIsImV4cCI6MTY3MTMxODUxNSwiaWF0IjoxNjcxMjMyMTE1LCJqdGkiOiI5YzQ1NGU0Mi03Mjc0LTQ1NWItYThlMy1mYjI3Y2IxYjEwZTEiLCJlbWFpbCI6Im1oaWV1c3ouMjMuZGtjQGdtYWlsLmNvbSJ9.JGTCotnQqv2Zhm45Guf6dwyMyCv1GxRNuVdaAqJ0lB9K0z3cSuD5GshbPbdqMZqeNn8uY13PVLXXpLsjWCOA8OrOVu_kN52xtyuRnL_myVxADeG54b0cnaWc9BcwTXp-BA67yw9ktw8s9jbZ8PAqbyloBG8q-KvTmGB_6Pnx6fVGm47rpQT9HtchXQfRdEb0NT0EOI9F2m_3SMOTmjBlomAViFXgAdl-H0Awr5vK0Bxm_PJE9vLMentIErOaNpzKKFHgsC7eAj7RwcEcfoKzQsK8GIn_PvI3wJSUiiCzeL9Lpc60DpsoDyOyTPVFjnVrnwmrrgRSoXQezj3SvLusEQ";
    @Headers({"accept: application/json",
            "x-api-key: hutech_hackathon@123456",
            "access-token: "+key})
    @GET("get_key")
     Call<GetKey> GetKeyAuthen() ;


    @POST("register")
    @Headers({"accept: application/json",
            "x-api-key: hutech_hackathon@123456",
            "Content-Type: application/json",
            "access-token: "+ key})
    Call<RegisterRes>getAccount(@Body Register acc) ;


    @POST("login")
    @Headers({"accept: application/json",
            "x-api-key: hutech_hackathon@123456",
            "Content-Type: application/json",
            "access-token: "+ key})
    Call<LoginRes>LoginAcount(@Body LoginReq acc) ;


    @POST("tranhis")
    @Headers({"accept: application/json",
            "Content-Type: application/json",
            "x-api-key: hutech_hackathon@123456",
            "access-token: "+ key})
    Call<TranhisRes>getTransHis(@Body TranhisReq acc) ;


    @POST("balance")
    @Headers({"accept: application/json",
            "Content-Type: application/json",
            "x-api-key: hutech_hackathon@123456",
            "access-token: "+ key})
    Call<BalanceRes>getAmount(@Body BalancReq acc) ;

    @POST("transfer")
    @Headers({"accept: application/json",
            "Content-Type: application/json",
            "x-api-key: hutech_hackathon@123456",
            "access-token: "+ key})
    Call<transferRes>getTransfer(@Body transferReq acc) ;


    @POST("change_password")
    @Headers({"accept: application/json",
            "Content-Type: application/json",
            "x-api-key: hutech_hackathon@123456",
            "access-token: "+ key})
    Call<resetPwRes>resetPW(@Body resetPwReq acc) ;



    @POST("user.php")
    @FormUrlEncoded
    Observable<database_postIn4>saveIn4   // dua thong tin pack up tren user
            (
        @Field("username") String username ,
        @Field("pass") String pass ,
        @Field("name") String name ,
        @Field("accNo") String accNo ,
        @Field("identity") String identity ,
        @Field("phone") String phone ,
        @Field("gmail") String gmail
    );

//    CompositeDisposable compositeDisposable = new CompositeDisposable() ;
//    Retrofit rs = new Retrofit.Builder()
//            .baseUrl("https://autofb18.net/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .build() ;
//    API api = rs.create(API.class) ;
//        compositeDisposable.add(api.saveIn4(App.getInstance().getStorage().username,App.getInstance().getStorage().passsword,
//    fullName,App.getInstance().getStorage().accountNo,identity,phone,email)
//        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(save_in4Res ->
//    {
//        Log.e(m003_VM.class.getName(),"HUHU" + save_in4Res.success );
//        Log.e(m003_VM.class.getName(),"co cai cc" + save_in4Res.message );
//    },
//    throwable ->
//    {
//        Log.e(m003_VM.class.getName(),"co cai cc" + throwable.getMessage() );
//    }
//                )
//                        );
//}




    @POST("games.php")
    @FormUrlEncoded
    Observable<database_postIn4>save_Game   //  lay thong tin game!
    (
            @Field("games") String games ,
            @Field("accNo") String accNo
    );





    @POST("getGames.php")
    @FormUrlEncoded
    Observable<database_getGames>getGame   //  lay thong tin game!
    (
            @Field("accNo") String accNo
    );


    @POST("getUser.php")
    @FormUrlEncoded
    Observable<database_getIn4>getUser   //  lay thong tin game!
    (
            @Field("accNo") String accNo
    );


}
