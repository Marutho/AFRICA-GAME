package com.mygdx.theafrica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;


public class ArcadeHandler implements ControllerListener {

    public WorldController wc;

    public  ArcadeHandler(WorldController worldc)
    {
        wc = worldc;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        //CONTROLLER 1
        if(Controllers.getControllers().indexOf(controller,true) == 0 && wc.levelManager.getPlayer(1).isTurn) {
            if(buttonCode == 0)
                wc.inputMgr.keySelectCard = true;

            if(buttonCode == 1)
                wc.inputMgr.keyEndTurn = true;

            if(buttonCode == 9)
                Gdx.app.exit();

            //man.switchColor(buttonCode);
            return true;
        }

        if(Controllers.getControllers().indexOf(controller,true) == 1 && wc.levelManager.getPlayer(2).isTurn) {
            if(buttonCode == 0)
                wc.inputMgr.keySelectCard = true;

            if(buttonCode == 1)
                wc.inputMgr.keyEndTurn = true;

            if(buttonCode == 9)
                Gdx.app.exit();

            //man.switchColor(buttonCode);
            return true;
        }

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        //Do nothing
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {


        //Controller 1
        if(Controllers.getControllers().indexOf(controller,true) == 0 && wc.levelManager.getPlayer(1).isTurn){

            //y axis
            if(axisCode == 0){

                //up
                if(value == -1){
                    wc.inputMgr.keyUp = true;
                    return true;
                }
                //down
                else if(value == 1){
                    wc.inputMgr.keyDown = true;
                    return true;
                }
                //center
                else if(value <0.1 && value>-0.1){

                    return true;
                }

            }
            //x axis
            else if(axisCode == 1){

                //left
                if(value == -1){
                    wc.inputMgr.keyLeft = true;
                    return true;
                }
                //right
                else if(value == 1){
                    wc.inputMgr.keyRight = true;
                    return true;
                }
                //center
                else if(value <0.1 && value>-0.1){
                    return true;
                }

            }
        }


        //Controller 2
        if(Controllers.getControllers().indexOf(controller,true) == 1 && wc.levelManager.getPlayer(2).isTurn){

            //y axis
            if(axisCode == 0){

                //up
                if(value == -1){
                    wc.inputMgr.keyUp = true;
                    return true;
                }
                //down
                else if(value == 1){
                    wc.inputMgr.keyDown = true;
                    return true;
                }
                //center
                else if(value <0.1 && value>-0.1){

                    return true;
                }

            }
            //x axis
            else if(axisCode == 1){

                //left
                if(value == -1){
                    wc.inputMgr.keyLeft = true;
                    return true;
                }
                //right
                else if(value == 1){
                    wc.inputMgr.keyRight = true;
                    return true;
                }
                //center
                else if(value <0.1 && value>-0.1){
                    return true;
                }

            }
        }

        return false;
    }

    @Override
    public void connected(Controller controller) {
        //Do nothing
    }

    @Override
    public void disconnected(Controller controller) {
        //Do nothing
    }
    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
