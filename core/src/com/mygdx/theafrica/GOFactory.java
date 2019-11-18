package com.mygdx.theafrica;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GOFactory {



   /*public static SpecialEnemy generateCard(LevelManager levelManager, float posX, float posY){

        Card cd = new cd(levelManager, posX, posY);
        return cd;
    }*/




  /*  public static TextureRegion cardFromTextureAtlas(String regionName){

        TextureAtlas txAtlas = new TextureAtlas("");
        TextureRegion txreg = txAtlas.findRegion(regionName);
        return txreg;
    }*/

/*
    public static Background generateBR(LevelManager levelManager, float x, float y, float width, float height)
    {
        float bgFactorWidth = Assets.getInstance().bg.getWidth() * 1/100f;
        float bgFactorHeight = Assets.getInstance().bg.getHeight() * 1/20f;
        Assets.getInstance().bgTR = new TextureRegion(Assets.getInstance().bg,(int)(bgFactorWidth*width),(int)(bgFactorHeight* height));
        Background generatedBg = new Background(levelManager,x,y,width,height);

        return generatedBg;
    }*/

    public static BackgroundMenu generateBRMenu(float x, float y, float width, float height)
    {
        float bgFactorWidth = Assets.getInstance().bgMenu.getWidth() * 1/100f;
        float bgFactorHeight = Assets.getInstance().bgMenu.getHeight() * 1/118f;
        Assets.getInstance().bgMenuTR = new TextureRegion(Assets.getInstance().bgMenu,(int)(bgFactorWidth*width),(int)(bgFactorHeight* height));
        BackgroundMenu generatedBgMenu = new BackgroundMenu(x,y,width,height);

        return generatedBgMenu;
    }

    public static Background generateBGWall(float x, float y, float width, float height)
    {
        float bgFactorWidth = Assets.getInstance().bgWall.getWidth() * 1/100f;
        float bgFactorHeight = Assets.getInstance().bgWall.getHeight() * 1/118f;
        Assets.getInstance().bgWallTR = new TextureRegion(Assets.getInstance().bgWall,(int)(bgFactorWidth*width),(int)(bgFactorHeight* height));
        Background generatedBGWall= new Background(x,y,width,height);

        return generatedBGWall;
    }
}