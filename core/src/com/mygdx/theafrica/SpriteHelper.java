package com.mygdx.theafrica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class SpriteHelper {


    public static Sprite spriteFromTextureRegion(String regionName)
    {

        TextureAtlas txAtlas = new TextureAtlas("mario/pack1.atlas");
        TextureRegion txreg = txAtlas.findRegion("mario1");
        Sprite spr = new Sprite(txreg);
        spr.setSize(1, 1);
        spr.setPosition(1, 1);
        spr.setOriginCenter();
        return spr;
    }



    public static TextureRegion textureFromTextureAtlas(String regionName, TextureAtlas txAtlas){

        return txAtlas.findRegion(regionName);

    }
}
