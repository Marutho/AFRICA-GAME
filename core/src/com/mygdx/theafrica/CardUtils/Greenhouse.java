package com.mygdx.theafrica.CardUtils;

import com.mygdx.theafrica.Player;

public class Greenhouse extends Upgrades{
//Pillar la imagen de Greenhouse
    public Greenhouse(){
        requiredWood = 5;
        requiredWheat = 5;
        requiredSeeds = 2;
    }

    @Override
    public void build(Player p) {
        p.currentWood -= requiredWood;
        p.currentWheat -= requiredWheat;
        p.currentSeeds -= requiredSeeds;

    }
}
