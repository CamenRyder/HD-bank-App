package com.example.project_a.View.FRG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_a.R;
import com.example.project_a.Storage.App;
import com.example.project_a.ViewModel.BaseViewModel_API;
import com.example.project_a.ViewModel.m014_VM;
import com.example.project_a.databinding.M004GameboughtBinding;
import com.example.project_a.otherClass.Games;

import java.util.List;

public class m014_frg extends baseFRG<M004GameboughtBinding, m014_VM> {
    @Override
    protected void initViews() {
        binding.lnGamesBought.removeAllViews();
        setListGame();
    }

    private void setListGame() {
        binding.lnGamesBought.removeAllViews();
        viewModel.getGameUBuy(App.getInstance().getStorage().accountNo);
    }

    @Override
    protected Class<m014_VM> ClassVM() {
        return m014_VM.class;
    }

    @Override
    protected M004GameboughtBinding initViewBindings(LayoutInflater inflater, ViewGroup container) {
        return M004GameboughtBinding.inflate(inflater,container,false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        super.apiSuccess(key, data);
        if(key.equals(m014_VM.GET_GAMES))
        {

            List<Games> games = (List<Games>)data ;
            for(int i=0;i<20;i++)
            {
                Log.e(m014_frg.class.getName(),"So luong trong game: " + games.size()) ;
                if(games.get(i).isUBuy == true)
                {
                    View view = getLayoutInflater().inflate(R.layout.item_listgame,null);
                    ImageView iv = view.findViewById(R.id.iv_m004Picgames) ;
                    TextView tv_iv_m004TitleGames = view.findViewById(R.id.iv_m004TitleGames);
                    TextView iv_m004Option = view.findViewById(R.id.iv_m004Option);
                    TextView iv_m004homeSource = view.findViewById(R.id.iv_m004homeSource);
                    TextView iv_m004release = view.findViewById(R.id.iv_m004release);
                    iv.setImageBitmap(games.get(i).picture);
                    tv_iv_m004TitleGames.setText(games.get(i).nameGame);
                    iv_m004Option.setText(games.get(i).option);
                    iv_m004homeSource.setText(games.get(i).publisher);
                    iv_m004release.setText(games.get(i).released);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                            Toast.makeText(context, "Phien chua cai game!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    binding.lnGamesBought.addView(view);
                }
            }

        }
    }
}
