package com.mygdx.theafrica.CardUtils;

import com.mygdx.theafrica.Player;

public class School extends Upgrades {
//Pillar la imagen de school
    public School(){
        requiredWood = 5;
        requiredIron = 5;
        requiredBooks = 2;
    }

    @Override
    public void build(Player p) {

        p.currentWood -= requiredWood;
        p.currentIron -= requiredIron;
        p.currentBooks -= requiredBooks;
    }


}
