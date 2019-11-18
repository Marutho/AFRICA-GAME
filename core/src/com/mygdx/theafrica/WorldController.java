package com.mygdx.theafrica;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.theafrica.HUD.HUD;
import com.mygdx.theafrica.HUD.TextButton;

import javax.naming.ldap.Control;
import java.util.ArrayList;

public class WorldController extends InputAdapter {

    public Sprite spr;
    //   public Sprite[] sprites;
    public String TAG_KEYS = "KEYS";
    public int selectedSprite;
    public ArrayList<GameObject> objects;
    public CameraHelper ch;

    HUD hud;

    public LevelManager levelManager = new LevelManager();
    public Background background;
    public InputManager inputMgr;
    public TextButton bUp, bDown, bLeft, bRight, bSelect, bAccel, bEndTurn;
    public static WorldController instance;


    public WorldController(){
        if(WorldController.instance ==null)
        {
            instance = this;
        }
        else if(WorldController.instance != this)
        {
            WorldController.instance = null;
        }

        inputMgr = new InputManager(this);
        Gdx.input.setInputProcessor(inputMgr);

        ch = new CameraHelper();

        hud = new HUD();

        if(Gdx.app.getType() == Application.ApplicationType.Android)
        {

            bUp = new TextButton("UP",1750,10,100,100){
                @Override
                public void click() {
                    inputMgr.keyUp = true;
                }
            };

            bDown = new TextButton("DWN",1750,-210,100,100){
                @Override
                public void click() {
                    Gdx.app.debug("INPUT","Touched Down");
                    levelManager.getMark().isTouchedDown = true;
                }
            };

            bLeft = new TextButton("LF",1650,-100,100,100){
                @Override
                public void click() {
                    inputMgr.keyLeft = true;
                }
            };


            bRight = new TextButton("RG",1850,-100,100,100){
                @Override
                public void click() {
                    inputMgr.keyRight = true;
                }
            };


            bSelect = new TextButton("SEL",250,10,100,100){
                @Override
                public void click() {
                    inputMgr.keySelectCard = true;
                }
            };

            bAccel = new TextButton("ACC",250,-110,100,100){
                @Override
                public void click() {
                    inputMgr.keyAccelerateWorker = true;
                }
            };

            bEndTurn = new TextButton("END",250,-220,100,100){
                @Override
                public void click() {
                    inputMgr.keyEndTurn = true;
                }
            };

            hud.add(bUp);
            hud.add(bDown);
            hud.add(bLeft);
            hud.add(bRight);
            hud.add(bSelect);
            hud.add(bAccel);
            hud.add(bEndTurn);
        }

        if(Controllers.getControllers().size > 0)
        {
            ArcadeHandler arcade = new ArcadeHandler(this);
            Controllers.addListener(arcade);

        }
        init();


    }

    public void init()
    {
        Grid grid = new Grid();
        levelManager.Instantiate(grid);
        levelManager.Instantiate(new Marker(grid));
        levelManager.Instantiate(new TurnManager());
    }

    public void update(float delta){

        levelManager.update(delta);
        ch.hudCamera.update();
        //ch.followGO(levelManager.player, levelManager.bg);
        ch.moveCamera(delta);


    }

    @Override
    public boolean keyDown (int keycode){
        //Gdx.app.debug(TAG_KEYS, keycode+" has been pressed");
        if(keycode==Input.Keys.I)
        {
            Gdx.app.debug("SPRITE", spr.getBoundingRectangle()+"");
        }
        return false;
    }
}
