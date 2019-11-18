package com.mygdx.theafrica;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class InputMenu implements InputProcessor {

    public SCREEN_MENU menu;

    public  InputMenu(SCREEN_MENU mn)
    {
        menu=mn;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    Vector3 pointMenu;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        int margin = 6;
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        pointMenu = new Vector3(screenX,screenY,0);
        //check if the click is for the HUD
        menu.hudCamera.unproject(pointMenu);
        if(!menu.hud.click(pointMenu.x,pointMenu.y)){
            System.out.println("Aqui deberian saltar moendos");
        }
        else
        {
            //menu.game.setScreen(menu.game.gameScreen);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
