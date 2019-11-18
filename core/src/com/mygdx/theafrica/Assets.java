package com.mygdx.theafrica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    private static Assets instance = null;

    public Texture bg;
    public TextureRegion tiledBg;


    //HUD
    public Texture button;
    public BitmapFont font;

    public TextureAtlas icons;

    public TextureAtlas cards;
    public TextureRegion[] cardsReg;

    public TextureRegion[] player; //Decidir luego en la clase player si se dibuja la ficha de player 1 o de player 2
    public TextureRegion hospital;
    public TextureRegion greenhouse;
    public TextureRegion school;

    public TextureRegion[] dice;
    //Marker
    public Texture marker;
    public TextureRegion[] markerReg;


    public TextureAtlas turns;
    public TextureRegion[] turnsTR;

    //MENU
    public Texture bgMenu;
    public TextureRegion bgMenuTR;

    //Wallpaper
    public Texture bgWall;
    public TextureRegion bgWallTR;

    private Assets() {


        //HUD
        button = createButtonTexture();
        font = new BitmapFont();
        font.getData().setScale(2);

        //MENU
        bgMenu = new Texture("Africa2.png");
        //bgMenu.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);

        //Wallpaper
        bgWall = new Texture("wallpaperNormal.jpg");
        bgWall.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);

        //BACKGROUND---------------------------------------------------------------------------------------------------------------------
        bg = new Texture (Gdx.files.internal("bg.png"));
        bg.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);

        //MARKER
        marker = new Texture(Gdx.files.internal("Marker.png"));
        markerReg = new TextureRegion[1];
        markerReg[0]= new TextureRegion(marker);

        float bgFactorWidth = bg.getWidth() * 1;        //this means that my Texture should cover 1 world unit width.

        float bgFactorHeight = bg.getHeight() * 1;   //this means that my Texture should cover 1 world unit height.

        float widthInWorldUnits = 20;                        //I am creating a world of 20 units width (my viewport width is around 10)

        float heightInWorldUnits = 20;                     //I am creating a world of 20 units height (my viewport height is fixed to 10)

        tiledBg = new TextureRegion(bg, Math.round(widthInWorldUnits*bgFactorWidth),Math.round(heightInWorldUnits*bgFactorHeight));

        //ICONS--------------------------------------------------------------------------------------------------------------------------
        icons = new TextureAtlas("Icons.atlas");
        player = new TextureRegion[2];
        dice = new TextureRegion[7];

        for(int i = 0; i<2; i++)
        {
            player[i] = SpriteHelper.textureFromTextureAtlas("icon"+(i), icons);
        }

        for(int i = 2; i<9; i++)
        {
            dice[(i-2)] = SpriteHelper.textureFromTextureAtlas("icon"+(i), icons);
        }

        hospital = SpriteHelper.textureFromTextureAtlas("icon"+(9), icons);
        greenhouse = SpriteHelper.textureFromTextureAtlas("icon"+(10), icons);
        school = SpriteHelper.textureFromTextureAtlas("icon"+(11), icons);


        //CARDS--------------------------------------------------------------------------------------------------------------------------
        cards = new TextureAtlas("Cards.atlas");
        int cardsNumber = cards.getRegions().size;
        cardsReg = new TextureRegion[cardsNumber];

        for(int i = 0; i<cardsNumber; i++)
        {
            cardsReg[i] = SpriteHelper.textureFromTextureAtlas("card"+(i), cards);
        }


        //TURNS--------------------------------------------------------------------------------------------------------------------------
        turns = new TextureAtlas("Turns.atlas");
        int turnsNumber = turns.getRegions().size;
        turnsTR = new TextureRegion[turnsNumber];

        for(int i = 0; i<turnsNumber; i++)
        {
            turnsTR[i] = SpriteHelper.textureFromTextureAtlas("Turn"+(i), turns);
        }
    }

    private Texture createButtonTexture() {
        Pixmap pm = new Pixmap(10,10, Pixmap.Format.RGBA8888);
        pm.setColor(0,0,0,1);
        pm.drawRectangle(0,0,10,10);
        pm.setColor(0,1,0,1);
        pm.fillRectangle(1,1,8,8);
        return new Texture(pm);
    }

    public static Assets getInstance() {
        if(instance == null) {
            instance = new Assets();
        }
        return instance;
    }

}
