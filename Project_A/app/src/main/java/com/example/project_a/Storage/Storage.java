package com.example.project_a.Storage;

import com.example.project_a.otherClass.Games;

import java.util.ArrayList;
import java.util.List;

public class Storage
{
        public String passsword;
        public String username ;
        public String key ;
        public String notifyCase = "Mua game thanh cong";
        public String accountNo  = "" ;
        public List<Games> listLocalGames = new ArrayList<>();

                public  void initStorage()
                {
                        notifyCase = "Mua game thanh cong";
                        listLocalGames = new ArrayList<>();
                        index = 0  ;
                }

        public int index = 0 ;
}
