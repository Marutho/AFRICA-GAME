package com.mygdx.theafrica.CardUtils;

import com.mygdx.theafrica.Player;

public abstract class Upgrades {

    public int requiredWood;
    public int requiredIron;
    public int requiredWheat;
    public int requiredBandages;
    public int requiredBooks;
    public int requiredSeeds;

     public abstract void build(Player p);

}
