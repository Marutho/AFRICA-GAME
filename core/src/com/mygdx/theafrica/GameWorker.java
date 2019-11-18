package com.mygdx.theafrica;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.theafrica.CardUtils.GameCard;

public class GameWorker extends GameObject{

    public int pNumber;
    public Player p;
    public boolean working; //Defines the current state of the worker
    public int workerNumber;
    public Vector2 targetPos;
    public GameCard gameCard; //The card that the worker receives as the place he needs to go, later obtained

    public GameWorker(int number, int workerN)
    {
        super();
        workerNumber = workerN;
        working = false;
        pNumber = number;
        layerTag = Layer.LayerNames.ICONS;
        targetPos = new Vector2();
        width = 15;
        height = 15;
        scale = new Vector2(3, 3);
    }


    public void getInitialPos()
    {
        int paddingX = 45;
        int paddingY = 10;
        for(int i = 0; i<3; i++)
        {
            if(p.number == 1)
            {
                if(workerNumber == 1)
                {
                    targetPos.x =  WorldController.instance.levelManager.getGrid().positions[0][0].x+paddingX;
                    targetPos.y = WorldController.instance.levelManager.getGrid().positions[0][0].y+paddingY;
                }

                else if(workerNumber == 2)
                {
                    targetPos.x =  WorldController.instance.levelManager.getGrid().positions[0][0].x+paddingX*2+5;
                    targetPos.y = WorldController.instance.levelManager.getGrid().positions[0][0].y+paddingY;
                }

                else if(workerNumber == 3)
                {
                    targetPos.x =  WorldController.instance.levelManager.getGrid().positions[0][0].x+paddingX*3+10;
                    targetPos.y = WorldController.instance.levelManager.getGrid().positions[0][0].y+paddingY;
                }
            }

            if(p.number == 2)
            {
                if(workerNumber == 1)
                {
                    targetPos.x =  WorldController.instance.levelManager.getGrid().positions[3][3].x+paddingX;
                    targetPos.y = WorldController.instance.levelManager.getGrid().positions[3][3].y+paddingY;
                }

                else if(workerNumber == 2)
                {
                    targetPos.x =  WorldController.instance.levelManager.getGrid().positions[3][3].x+paddingX*2+5;
                    targetPos.y = WorldController.instance.levelManager.getGrid().positions[3][3].y+paddingY;
                }

                else if(workerNumber == 3)
                {
                    targetPos.x =  WorldController.instance.levelManager.getGrid().positions[3][3].x+paddingX*3+10;
                    targetPos.y = WorldController.instance.levelManager.getGrid().positions[3][3].y+paddingY;
                }
            }
        }

    }
    public void getBackToBase()
    {
        working = false;
        //getInitialPos();
        x = targetPos.x;
        y = targetPos.y;
    }

    public void goToCard(GameCard gc)
    {
        gc.player = p;
        working = true;
        gameCard = gc;
        x = gc.playerPosition.x;
        y = gc.playerPosition.y;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texRegionToDraw(pNumber),x,y,0,0,width,height,scale.x,scale.y,rotation);
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {

    }

    @Override
    public void update(float delta) {

        if(p == null)
        {
            p = WorldController.instance.levelManager.getPlayer(pNumber);
        }

        if(!working)
        {
            getInitialPos();
            x = targetPos.x;
            y = targetPos.y;
        }

    }
    TextureRegion texRegionToDraw(int i)
    {
        return Assets.getInstance().player[i-1];
    }


}

