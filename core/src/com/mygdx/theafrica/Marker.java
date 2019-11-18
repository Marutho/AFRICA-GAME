package com.mygdx.theafrica;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Marker extends GameObject {

    public int index; //Lo adquiere del index de la casilla de la grid
    public boolean hasPlayer;
    public int usageTurns; //Numero de turnos que le queda a una carta que esta siendo usada
    public int column;
    public int row;
    InputManager inputs;
    Grid grid;
    int padding = 15;
    public boolean allowLosingActionOnMovement = true;

    public boolean isTouchedDown = false;

    public Marker(Grid gr)
    {
        inputs=WorldController.instance.inputMgr;
        column=1;
        row=0;
        grid = gr;
        x = gr.positions[0][1].x;
        y = gr.positions[0][1].y;

        layerTag = Layer.LayerNames.DEFAULT ;
        width = 106;
        height = 106;
        scale = new Vector2(2.5f,2.5f);

    }


    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(Assets.getInstance().markerReg[0],x,y,0,0,width,height,scale.x,scale.y,rotation);
    }


    @Override
    public void update(float delta) {
        //(TurnManager.instance.activePlayer.actionNumber>1)
            moveMarker();
        x = grid.positions[row][column].x-padding;
        y = grid.positions[row][column].y-padding;
    }

    public void setMarkerOnPlayer(int number)
    {
        if(number == 1)
        {
            row = 0; column = 0;
            System.out.println("Marker set on player 1");
        }
        if(number == 2)
        {
            row = 3; column = 3;
            System.out.println("Marker set on player 2");
        }
    }

    public void moveMarker()
    {
        if(inputs.keyUp)
        {
            if(row-1<1 && column == 0)
            {
                return;
            }
            else if((row-1)<0)
            {
                System.out.println("Que te vaaaas chaval!");
            }else
            {
                row--;
                if(allowLosingActionOnMovement)TurnManager.instance.activePlayer.actionNumber --;
                SoundManager.reproduceSounds(6);
            }
        }
        else if(inputs.keyDown || isTouchedDown)
        {
            isTouchedDown = false;
            if(row+1>=3 && column == 3)
            {
                return;
            }
            else if((row+1)>=4)
            {
                System.out.println("Que te vaaaas chaval!");
            }else
            {
                row++;
                if(allowLosingActionOnMovement)TurnManager.instance.activePlayer.actionNumber --;
                SoundManager.reproduceSounds(6);
            }
        }
        else if(inputs.keyLeft)
        {
            if((column-1)<1 && row == 0)
            {
                return;
            }
            else if((column-1)<0)
            {
                System.out.println("Que te vaaaas chaval!");
            }else
            {
                column--;
                if(allowLosingActionOnMovement)TurnManager.instance.activePlayer.actionNumber --;
                SoundManager.reproduceSounds(6);
            }
        }
        else if(inputs.keyRight)
        {
            if((column+1)>=3 && row == 3)
            {
                return;
            }
            if((column+1)>=4)
            {
                System.out.println("Que te vaaaas chaval!");
            }else
            {
                column++;
                if(allowLosingActionOnMovement)TurnManager.instance.activePlayer.actionNumber --;
                SoundManager.reproduceSounds(6);
            }
        }

    }
    @Override
    public void draw(SpriteBatch batch, float staTime) {

    }
}
