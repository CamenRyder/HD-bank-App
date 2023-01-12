package com.example.project_a.ViewModel;

import android.util.Log;
import android.widget.Toast;

import com.example.project_a.API.API;
import com.example.project_a.API.Req.LoginReq;
import com.example.project_a.API.Req.Register;
import com.example.project_a.API.Res.LoginRes;
import com.example.project_a.API.Res.RegisterRes;
import com.example.project_a.API.RetrofitClient;
import com.example.project_a.Storage.App;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class m003_VM extends BaseViewModel_API{

    public static final String REGISTER_KEY ="REGISTER_KEY" ;
    private static final String LOGIN_KEY = "LOGIN_KEY";
    public static final String GETACCOUNTNO = "GETACCOUNTNO";
    public String email ;
    public String phone ;
    public String fullName ;
    public String identity ;
    public String credential ;
    private String credential2;
    private String credentialTmp;
    private String stk;

    public void getResiger()
    {
         credentialTmp = "{\"username\":\"" + App.getInstance().getStorage().username
                + "\",\"password\":\"" + App.getInstance().getStorage().passsword + "\"\n" +
                "}";
            try{
                credential =  Base64.getEncoder().encodeToString(encrypt(credentialTmp, App.getInstance().getStorage().key));
                Log.e(m003_VM.class.getName(),"Credential: "+ credential) ;
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        getAPI().getAccount(new Register(credential,email,fullName,identity,App.getInstance().getStorage().key,phone)).enqueue(initHandleResponse(REGISTER_KEY));

    }

    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }
//Format message invalid, email format invalid, phoneNumber should be 10-11 numbers and start with 0
    @Override
    protected void handleSuccess(String key, Object body) {
        super.handleSuccess(key, body);
        String notify ="" ;

        if(key.equals(REGISTER_KEY))
        {
            RegisterRes res =(RegisterRes) body ;
            Log.e(m003_VM.class.getName(),res.toString()) ;
            if(res.response.responseMessage.contains("email format invalid"))
                notify += "kiểu email của bạn bị sai, ";
            if(res.response.responseMessage.contains("phoneNumber should be 10-11 numbers and start with 0"))
                notify += "số điện thoại phải bắt đầu bằng 0 và đừng có chữ, ";
            if(res.response.responseMessage.contains("identityNumber should only include number"))
                notify += "Lỗi số căn cước," ;

            if(res.response.responseCode.equals("00"))
            {

                try{
                    credential =  Base64.getEncoder().encodeToString(encrypt(credentialTmp, App.getInstance().getStorage().key));
                    Log.e(m003_VM.class.getName(),"Credential: "+ credential) ;
                    callBack.apiSuccess(REGISTER_KEY, res ); // note lấy dữ liệu 2 lần!
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }

        App.getInstance().getStorage().notifyCase = notify ;
    }
    public  void getAccountNO() {


        Retrofit rs = new Retrofit.Builder().baseUrl(BASER_URL)
                .addConverterFactory(GsonConverterFactory.create()).build() ;
        API api = rs.create(API.class) ;

        api.LoginAcount(new LoginReq(credential,App.getInstance().getStorage().key))
                .enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                        saveData(response.body().data.accountNo);


                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {
                        Log.e(m003_VM.class.getName(),"Khong on roi: " + t.getMessage());
                    }
                });

    }

    public void saveData(String accont) {
        CompositeDisposable compositeDisposable = new CompositeDisposable() ;
        Retrofit rs = new Retrofit.Builder()
                .baseUrl("https://autofb18.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build() ;
        API api = rs.create(API.class) ;
        compositeDisposable.add(api.saveIn4(App.getInstance().getStorage().username,App.getInstance().getStorage().passsword,
                        fullName,accont,identity,phone,email)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(save_in4Res ->
                        {

                            Log.e(m003_VM.class.getName(),"HUHU" + save_in4Res.success );
                            Log.e(m003_VM.class.getName(),"tai khoan la: " + accont  );
                            Log.e(m003_VM.class.getName(),"Day la thanh cong! : " + save_in4Res.message );
                        },
                        throwable ->
                        {
                            Log.e(m003_VM.class.getName(),"co cai cc   " + throwable.getMessage() );
                        }
                )
        );
    }


}
