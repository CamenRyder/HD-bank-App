package com.example.project_a.View.FRG;

import android.icu.util.Calendar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project_a.API.Res.TranhisRes;
import com.example.project_a.R;
import com.example.project_a.ViewModel.CommonVm;
import com.example.project_a.databinding.M015Content3Binding;

import org.w3c.dom.Text;


public class m015_content_3 extends baseFRG<M015Content3Binding, CommonVm> {
    String fromDate = "";
    String toDate =" " ;
    public m015_content_3() {
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }
    private String current = "";
    private String ddmmyyyy = "DDMMYYYY";
    private Calendar cal = Calendar.getInstance();

    @Override
    protected void initViews() {
                binding.edtFromDate.addTextChangedListener(new TextWatcher() {
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
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            binding.edtFromDate.setText(current);
                            binding.edtFromDate.setSelection(sel < current.length() ? sel : current.length());



                        }
                    }


                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void afterTextChanged(Editable s)
                    {

                    }
                });

    }
        public void addViewHistory(Object objects)
        {
            binding.lnAddHistory.removeAllViews();
            TranhisRes res = (TranhisRes) objects ;
            if(res.data == null)
            {
                Log.e(m015_content_3.class.getName(), "Du lieu bi null@!2") ;
            }
//          int index =    res.data.transHis.size() ;
//          for(int i=0 ; i < index ; i++)
//          {
//              View view = getLayoutInflater().inflate(R.layout.item_trade, null) ;
//              TextView Date = view.findViewById(R.id.tv_dateToTrade) ;
//              Date.setText(res.data.transHis.get(i).transDate);
//              TextView Price = view.findViewById(R.id.tv_priceToTrade) ;
//              Price.setText(res.data.transHis.get(i).transDate);
//              TextView Des = view.findViewById(R.id.tv_contentToTrade) ;
//              Des.setText(res.data.transHis.get(i).transDate);
//              binding.lnAddHistory.addView((view));
//          }
        }
    @Override
    protected Class<CommonVm> ClassVM() {
        return CommonVm.class;
    }

    @Override
    protected M015Content3Binding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M015Content3Binding.inflate(inflater,container,false);
    }
}
