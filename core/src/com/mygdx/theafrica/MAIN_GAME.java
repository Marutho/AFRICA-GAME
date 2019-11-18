package com.mygdx.theafrica;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;

public class MAIN_GAME extends Game {

    SCREEN_MENU menuScreen;
    SCREEN_MAIN gameScreen;
    public static MAIN_GAME instance;

    @Override
    public void create() {
        if(MAIN_GAME.instance ==null)
        {
            instance = this;
        }
        else if(MAIN_GAME.instance != this)
        {
            WorldController.instance = null;
        }
        menuScreen = new SCREEN_MENU(this);

        if(Gdx.app.getType() == Application.ApplicationType.Android)
            System.out.println("Estoy en android");
        else if (Gdx.app.getType() == Application.ApplicationType.Desktop){

            if(Controllers.getControllers().size > 0){

            }
        }

        setScreen(menuScreen);
    }

    public void InitializeGame()
    {
        gameScreen = new SCREEN_MAIN(this);
    }
}
