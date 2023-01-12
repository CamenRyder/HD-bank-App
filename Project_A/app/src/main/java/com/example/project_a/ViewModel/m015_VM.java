package com.example.project_a.ViewModel;

import android.util.Log;

import com.example.project_a.API.API;
import com.example.project_a.API.Req.BalancReq;
import com.example.project_a.API.Req.TranhisReq;
import com.example.project_a.API.Req.transferReq;
import com.example.project_a.API.Res.BalanceRes;
import com.example.project_a.API.Res.TranhisRes;
import com.example.project_a.API.Res.transferRes;
import com.example.project_a.Storage.App;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class m015_VM extends BaseViewModel_API{

    public static final String GET_TRANSFER = "GET_TRANSFER";
    public static final String GET_USER = "GET_USER";


    public void getUser(String accNo)
    {

    CompositeDisposable compositeDisposable = new CompositeDisposable() ;
    Retrofit rs = new Retrofit.Builder()
            .baseUrl("https://autofb18.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build() ;
    API api = rs.create(API.class) ;
        compositeDisposable.add(api.getUser(accNo)
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(database_getIn4 ->
    {
        callBack.apiSuccess(GET_USER,database_getIn4 );
        Log.e(m015_VM.class.getName(),"ten ne: " + database_getIn4.name );
        Log.e(m015_VM.class.getName(),"doi tuong ne : " + database_getIn4.username );
        Log.e(m015_VM.class.getName(),"gi do : " + database_getIn4.gmail );
        Log.e(m015_VM.class.getName(),"va gi do nua: " + database_getIn4.phone );
    },
    throwable ->
    {
        Log.e(m003_VM.class.getName(),"co cai cc" + throwable.getMessage() );
    }
                )
                        );
}



    public void getTransfer(String amount, String desC, String toAcct)
    {
        getAPI().getTransfer(new transferReq(amount,desC,toAcct)).enqueue(initHandleResponse(GET_TRANSFER));
    }

    public static final String GET_TRANSHIS = "GET_TRANSHIS";
    public static final String GET_AMOUNT = "GET_AMOUNT" ;

    public void  getTransHis(String fromDate, String toDate){
              getAPI().getTransHis(new TranhisReq(App.getInstance()
                      .getStorage().accountNo,fromDate,toDate )).enqueue(initHandleResponse(GET_TRANSHIS));
      }

      public void getAmount()
      {
          getAPI().getAmount(new BalancReq()).enqueue(initHandleResponse(GET_AMOUNT));
      }

    public static String price = "0" ;

    public  String getPrice() {
        return price;
    }

    @Override
    protected void handleSuccess(String key, Object body) {
        super.handleSuccess(key, body);
        if(key.equals(GET_TRANSHIS))
        {
            TranhisRes res = (TranhisRes) body ;
            if(res.response.responseCode.equals("00"))
            {
                Log.e(m015_VM.class.getName(),res.response.responseMessage) ;
            }

        }else if (key.equals(GET_AMOUNT))
        {
            BalanceRes res1 = (BalanceRes) body ;
            Log.e(m015_VM.class.getName(),res1.response.responseMessage) ;
            if(res1.response.responseCode.equals("00"))
            {
                Log.e(m015_VM.class.getName(),res1.response.responseMessage) ;
                price = res1.data.amount ;
            }


        }else if(key.equals(GET_TRANSFER))
        {
            transferRes res = (transferRes)body ;
            Log.e(m015_VM.class.getName(), res.response.responseMessage) ;
            Log.e(m015_VM.class.getName(), res.response.responseCode) ;
//            if(res.response.responseCode.equals("03"))
//            {
//                Log.e(m015_VM.class.getName(),"Chuyen tien thanh cong? : "+res.response.responseMessage) ;
//            }
            callBack.apiSuccess(GET_TRANSFER,res);
        }

        Log.e(m015_VM.class.getName(),"Chuyen tien thanh cong! ");
    }

    @Override
    protected void handleFail(String key, int code, ResponseBody errorBody) {
        super.handleFail(key, code, errorBody);
        if(key.equals(GET_TRANSHIS))
        {
            Log.e(m015_VM.class.getName(),errorBody.toString());
            Log.e(m015_VM.class.getName(),"That bai! ");
        }else if(key.equals(GET_TRANSFER))
        {
            Log.e(m015_VM.class.getName(),errorBody.toString());
            Log.e(m015_VM.class.getName(),"That bai! ");
        }

    }
}
