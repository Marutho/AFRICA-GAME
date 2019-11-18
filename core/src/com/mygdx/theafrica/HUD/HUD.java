package com.mygdx.theafrica.HUD;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class HUD extends HUDElement {

    ArrayList<TextButton> elements;

    public HUD(){
        elements = new ArrayList<TextButton>();
    }

    public void add(TextButton go){
        elements.add(go);
    }

    @Override
    public void render(SpriteBatch batch) {
        for(HUDElement he : elements){
            he.render(batch);
        }
    }

    public boolean click(float x, float y) {

        for(TextButton b : elements){
            if(b.contains(x,y)){
                b.click();
                return true;
            }
        }
        return false;
    }
}

