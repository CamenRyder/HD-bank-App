package com.example.project_a.ViewModel;

import android.util.Log;

import com.example.project_a.API.API;
import com.example.project_a.API.Req.LoginReq;
import com.example.project_a.API.Res.GetKey;
import com.example.project_a.API.Res.LoginRes;
import com.example.project_a.R;
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

import okhttp3.ResponseBody;

public class m001_VM extends BaseViewModel_API {
    public static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";
    private String credential;
    public int notify;
    public static final String GET_KEY = "GET_KEY";

    public void LoginAcc(String username, String password) {
        String credentialTmp = "{\"username\":\"" + username
                + "\",\"password\":\"" + password + "\"\n" +
                "}";
        Log.e(m001_VM.class.getName(),"Ma giai chua derytp" + credentialTmp) ;
        try {
            credential = Base64.getEncoder().encodeToString(encrypt(credentialTmp, App.getInstance().getStorage().key));
            Log.e(m003_VM.class.getName(), "Credential: " + credential);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getAPI().LoginAcount(new LoginReq(credential, App.getInstance().getStorage().key)).enqueue(initHandleResponse(LOGIN_ACCOUNT));
    }

    public static PublicKey getPublicKey(String base64PublicKey) {
        PublicKey publicKey = null;
        try {
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

    public void get_Key() {
        getAPI().GetKeyAuthen().enqueue(initHandleResponse(GET_KEY));
    }


    @Override
    protected void handleSuccess(String key, Object body) {
        super.handleSuccess(key, body);
        if (key.equals(GET_KEY)) {
            GetKey getKey = (GetKey) body;
            Log.e(m001_VM.class.getName(), getKey.toString());
        }
        else if(key.equals(LOGIN_ACCOUNT))
        {
            LoginRes loginRes = (LoginRes) body ;
          App.getInstance().getStorage().accountNo = loginRes.data.accountNo ;
        }

    }


}

