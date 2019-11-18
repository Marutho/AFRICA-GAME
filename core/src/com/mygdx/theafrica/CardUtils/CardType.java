package com.mygdx.theafrica.CardUtils;

public enum CardType {

    WHEAT(0),
    WOOD(1),
    IRON(2),
    BOOK(3),
    BANDAGE(4),
    SEED(5);
    //BASE;

    private int value;

    CardType(int value){this.value = value;}
    public int getValue(){return value;}

}
