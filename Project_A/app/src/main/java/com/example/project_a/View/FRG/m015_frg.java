package com.example.project_a.View.FRG;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project_a.API.Res.BalanceRes;
import com.example.project_a.API.Res.TranhisRes;
import com.example.project_a.API.Res.database_getIn4;
import com.example.project_a.API.Res.transferRes;
import com.example.project_a.R;
import com.example.project_a.Storage.App;
import com.example.project_a.ViewModel.m015_VM;
import com.example.project_a.databinding.M015In4Binding;
import com.example.project_a.otherClass.resetPassword;

import org.w3c.dom.Text;

public class m015_frg extends baseFRG<M015In4Binding, m015_VM> {
    private database_getIn4 responseUser;

    @Override
    protected void initViews() {
        viewModel.getAmount();
       viewModel.getUser(App.getInstance().getStorage().accountNo);
        binding.btTransfer.setColorFilter(getResources().getColor(R.color.Mint07));
       binding.btTransfer.setOnClickListener(v -> gotoTransfer(v));
       binding.btFriendtransfer.setOnClickListener(v -> gotoFriendtransfer(v));
       binding.btGetPrice.setOnClickListener(v -> gotoPrice(v));
       binding.btHistory.setOnClickListener(v -> gotoHistory(v));



        if(data != null)
        {
            OpenDrawer();
        }
        binding.tvResetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                      startActivity(new Intent(context,resetPassword.class));
                }
            });
        binding.tvAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
//                startActivity(new Intent(context,resetPassword.class));
            }
        });
        binding.tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
//                startActivity(new Intent(context,resetPassword.class));
            }
        });
        binding.tvRepairIn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
//                startActivity(new Intent(context,resetPassword.class));
            }
        });



    }

    private void OpenDrawer() {
        binding.DLDrawer.open();
    }

    public static String fromDateHis = "";
    public static String toDateHis ="" ;
    private String current = "";
    private String current2 = "";
    private String ddmmyyyy = "DDMMYYYY";
    private String ddmmyyyy2 = "DDMMYYYY";
    private Calendar cal = Calendar.getInstance();
    private Calendar cal2 = Calendar.getInstance();
    private LinearLayout ln_content ;
    private void gotoHistory(View v) {

        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        setColorDefault();
        binding.btHistory.setColorFilter(getResources().getColor(R.color.Mint07));
        binding.lnContentHDBANK.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.m015_content_3,null) ;
        ln_content = view.findViewById(R.id.ln_add_history);
        binding.lnContentHDBANK.addView(view);
        Button check = view.findViewById(R.id.bt_check) ;
        EditText fromDate = view.findViewById(R.id.edt_fromDate) ;
        EditText toDate = view.findViewById(R.id.edt_toDate) ;

        fromDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");

                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                        fromDateHis = clean ;
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    fromDate.setText(current);
                    fromDate.setSelection(sel < current.length() ? sel : current.length());
                    Log.e(m015_frg.class.getName(),"Ngay nhap his: " + fromDateHis) ;

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                Log.e(m015_frg.class.getName(),s.toString());
            }
        });

        toDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current2)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");

                    String cleanC = current2.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy2.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal2.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal2.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal2.getActualMaximum(Calendar.DATE))? cal2.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                        toDateHis = clean ;
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current2 = clean;
                    toDate.setText(current2);
                    toDate.setSelection(sel < current2.length() ? sel : current2.length());
                    Log.e(m015_frg.class.getName(),"Ngay nhap his: " + toDateHis) ;

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                Log.e(m015_frg.class.getName(),s.toString());
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(m015_frg.class.getName(),"Nhan su kien Button") ;
//                    Log.e(m015_frg.class.getName(),"Nhan su kien chuoi: " + fromDate.getText().toString()) ;
                producerHistory(v) ;
            }
        });

    }
    private void producerHistory(View view) {
                viewModel.getTransHis(fromDateHis,toDateHis);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        super.apiSuccess(key, data);


        if(key.equals(m015_VM.GET_USER))
        {
             responseUser = (database_getIn4)data ;
            binding.tvM041Name.setText(responseUser.name);

        }
            if(key.equals(m015_VM.GET_TRANSFER))
            {
                transferRes res = (transferRes)data ;
                if(res.response.responseCode.equals("00"))
                {
                    Toast.makeText(context, "Chuyển tiền thành công!", Toast.LENGTH_SHORT).show();
                }else  Toast.makeText(context, "Vì lí do nào đó, chuyển tiền thất bại!", Toast.LENGTH_SHORT).show();
            }
        if(key.equals(viewModel.GET_TRANSHIS) ) {
            TranhisRes res = (TranhisRes)data ;
            Log.e(m015_frg.class.getName(),"Du lieu: " + res.response.responseMessage);
            Log.e(m015_frg.class.getName(),"Source code : " + res.response.responseCode);
                if(res.response.responseCode.equals("00"))
                {
                    ln_content.removeAllViews();
                    Log.e(m015_frg.class.getName(),"Source code : " + res.data.transHis.size());
                    for(int i=0;i< res.data.transHis.size();i++)
                    {
                        View view = getLayoutInflater().inflate(R.layout.item_trade,null);
                        TextView tv_dateToTrade = view.findViewById(R.id.tv_dateToTrade) ;
                        TextView tv_contentToTrade =  view.findViewById(R.id.tv_contentToTrade) ;
                        TextView tv_priceToTrade = view.findViewById(R.id.tv_priceToTrade) ;
                        tv_dateToTrade.setText(res.data.transHis.get(i).transDate);
                        tv_contentToTrade.setText(res.data.transHis.get(i).transDesc);
                        tv_priceToTrade.setText(res.data.transHis.get(i).transAmount);
                        ln_content.addView(view);
                    }
                }
        }else if(key.equals(viewModel.GET_AMOUNT))
        {
            BalanceRes res = (BalanceRes)data ;
            Log.e(m015_frg.class.getName(),"So tien trong tai khoan: "  + res.data.amount.toString() ) ;
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        super.apiError(key, code, data);
    }

    private void gotoPrice(View v) {

        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        viewModel.getAmount();
        setColorDefault();
        binding.btGetPrice.setColorFilter(getResources().getColor(R.color.Mint07));
        binding.lnContentHDBANK.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.m015_content_2,null) ;
        TextView tv_money =  view.findViewById(R.id.tv_myAmount) ;
        tv_money.setText(viewModel.getPrice());
        TextView tv_myAccount =  view.findViewById(R.id.tv_MyID) ;
        Log.e(m015_frg.class.getName(),"So tai khoan la: "+ App.getInstance().getStorage().accountNo) ;
        tv_myAccount.setText(App.getInstance().getStorage().accountNo);
        binding.lnContentHDBANK.addView(view);
    }

    private void gotoFriendtransfer(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        setColorDefault();
        binding.btFriendtransfer.setColorFilter(getResources().getColor(R.color.Mint07));
        binding.lnContentHDBANK.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.m015_content_4,null) ;
        TextView tv_m04_email = view.findViewById(R.id.tv_m04_email)  ;
        tv_m04_email.setText(responseUser.gmail);
        TextView tv_m04_identity = view.findViewById(R.id.tv_m04_identity)  ;
        tv_m04_identity.setText(responseUser.identity);
        TextView tv_m04_phone = view.findViewById(R.id.tv_m04_phone)  ;
        tv_m04_phone.setText(responseUser.phone);
        TextView tv_m04_fName = view.findViewById(R.id.tv_m04_fName)  ;
        tv_m04_fName.setText(responseUser.name);
        TextView tv_m04_idTransfer = view.findViewById(R.id.tv_m04_idTransfer)  ;
        tv_m04_idTransfer.setText(responseUser.accNo);
        TextView tv_m04_userName = view.findViewById(R.id.tv_m04_userName)  ;
        tv_m04_userName.setText(responseUser.username);
        TextView tv_m04_password = view.findViewById(R.id.tv_m04_password)  ;
        tv_m04_password.setText(responseUser.pass);


        binding.lnContentHDBANK.addView(view);
    }

    EditText edt_stk,edt_money,edt_Desc ;
    private void  gotoTransfer(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        setColorDefault();
        binding.btTransfer.setColorFilter(getResources().getColor(R.color.Mint07));
        binding.lnContentHDBANK.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.m015_content_1,null) ;
        binding.lnContentHDBANK.addView(view);
         edt_stk = view.findViewById(R.id.edt_stk);
         edt_money = view.findViewById(R.id.edt_money);
         edt_Desc = view.findViewById(R.id.edt_Desc) ;

        producerTransfer(view) ;


    }

    private void producerTransfer(View view)
    {

           Button bt_continue = view.findViewById(R.id.bt_continue) ;



           bt_continue.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String stk = edt_stk.getText().toString() ;
                   String money = edt_money.getText().toString() ;
                   String DesC = edt_Desc.getText().toString() ;
                   Log.e(m015_frg.class.getName(),"Tai khoan ngan hang khi nhap: "+ stk);
                   Log.e(m015_frg.class.getName(),"Tai khoan ngan hang edt_money: "+ money);
                   Log.e(m015_frg.class.getName(),"Tai khoan ngan hang edt_Desc: "+ DesC);
                   viewModel.getTransfer(money,DesC,stk) ;
               }
           });
    }

    private void setColorDefault() {
        binding.btFriendtransfer.setColorFilter(getResources().getColor(R.color.Mint05));
        binding.btGetPrice.setColorFilter(getResources().getColor(R.color.Mint05));
        binding.btTransfer.setColorFilter(getResources().getColor(R.color.Mint05));
        binding.btHistory.setColorFilter(getResources().getColor(R.color.Mint05));
    }

    private void setITem_menu() {

    }

    @Override
    protected Class<m015_VM> ClassVM() {
        return m015_VM.class;
    }


    @Override
    protected M015In4Binding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M015In4Binding.inflate(inflater,container,false);
    }
}
