package com.mygdx.theafrica.CardUtils;

import com.mygdx.theafrica.Player;

public class Hospital extends Upgrades {
    //Pillar la imagen de hospital
    public Hospital(){
        requiredWheat = 5;
        requiredIron = 5;
        requiredBandages = 2;
    }

    @Override
    public void build(Player p) {
        //Con la posición que hemos cogido del player.playerbase.hospitalpos ponemos la imagen encima
        p.currentWheat -= requiredWheat;
        p.currentIron -= requiredIron;
        p.currentBandages -= requiredBandages;
    }
}
