package com.mygdx.theafrica;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

public class InputManager implements InputProcessor {

    public boolean keyRight = false;
    public boolean keyLeft = false;
    public boolean keyUp = false;
    public boolean keyDown = false;
    public boolean keySelectCard = false;
    public boolean keyAccelerateWorker = false;
    public boolean keyEndTurn = false;
    public boolean keyDeny = false;

    public Vector3 pointBut;

    WorldController wc;

    public InputManager(WorldController w)
    {
        wc = w;
    }


    public void resetBools()
    {
        keyRight = false;
        keyLeft = false;
        keyUp = false;
        keyDown = false;
        keySelectCard = false;
        keyAccelerateWorker = false;
        keyEndTurn = false;
        keyDeny = false;
    }

    public boolean keyDown (int keycode) {

        switch(keycode)
        {
            case Input.Keys.D: keyRight = true;
                break;
            case Input.Keys.A: keyLeft = true;
                break;
            case Input.Keys.W: keyUp = true;
                break;
            case Input.Keys.S: keyDown  = true;
                break;
            case Input.Keys.SPACE: keySelectCard = true;
                break;
            case Input.Keys.SHIFT_RIGHT: keyAccelerateWorker = true;
                break;
            case Input.Keys.ENTER: keyEndTurn = true;
                break;
            case Input.Keys.ESCAPE: keyDeny = true;
                break;
        }

        return true;
    }

    public boolean keyUp (int keycode) {

        switch(keycode)
        {
            case Input.Keys.D: keyRight = false;
                break;
            case Input.Keys.A: keyLeft = false;
                break;
            case Input.Keys.W: keyUp = false;
                break;
            case Input.Keys.S: keyDown  = false;
                break;
            case Input.Keys.SPACE: keySelectCard = false;
                break;
            case Input.Keys.SHIFT_RIGHT: keyAccelerateWorker = false;
                break;
            case Input.Keys.ENTER: keyEndTurn = false;
                break;
            case Input.Keys.ESCAPE: keyDeny = false;
                break;
        }

        return true;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {

        pointBut = new Vector3(x,y,0);
        //check if the click is for the HUD
        wc.ch.hudCamera.unproject(pointBut);
        if(!wc.hud.click(pointBut.x,pointBut.y)){
            System.out.println("Aqui deberian saltar moendos");
        }
        return true;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }
}
