package com.mygdx.theafrica;

import com.badlogic.gdx.Game;

import java.util.ArrayList;

public class Layer {

    public ArrayList<GameObject> list;
    public enum LayerNames{BACKGROUND, CARD, PLAYER, ICONS, DEFAULT};
    LayerNames name;

    public Layer (LayerNames _name)
    {
        name = _name;
        list = new ArrayList<GameObject>();
    }
}
