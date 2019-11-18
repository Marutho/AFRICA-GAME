package com.mygdx.theafrica;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.theafrica.CardUtils.GameCard;
import com.mygdx.theafrica.CardUtils.PlayerBase;

import java.util.ArrayList;


public class Player extends GameObject {
    public int number;
    public PlayerBase base;

    public int currentWood;
    public int currentWheat;
    public int currentIron;
    public int currentBandages;
    public int currentBooks;
    public int currentSeeds;
    public int maxWorkers = 3;
    public int actionNumber;//number of moves the player has
    public int buildingsNumber = 0;

    public GameWorker[] gwArray;

    public void initializeWorkers()
    {
        for(int i=0; i<maxWorkers; i++)
        {
          GameWorker gw = new GameWorker(this.number,i+1);
          gwArray[i] = gw;
          WorldController.instance.levelManager.Instantiate(gwArray[i]);
        }
    }

    public InputManager inputs;

    public boolean turnPassed;
    public boolean isTurn;

    public Player(int num, float posX, float posY) {
        super();
        inputs  = WorldController.instance.inputMgr;
        x=posX;
        y=posY;
        rotation = 0;
        currentWood=    0;
        currentWheat=   0;
        currentIron=    0;
        currentBandages=0;
        currentBooks=   0;
        currentSeeds=   0;
        width= 15;
        height=15;

        scale = new Vector2(5f,5f);

        isTurn = false;
        number = num; //1 or 2
        layerTag = Layer.LayerNames.PLAYER;
        rectangle = new Rectangle();
        base = new PlayerBase(number, posX, posY, this);
        WorldController.instance.levelManager.Instantiate(base);
        gwArray = new GameWorker[maxWorkers];
        initializeWorkers();

    }

    @Override
    public void draw(SpriteBatch batch)
    {
        if(actionNumber>=0)
        {
            if(number == 1)
                batch.draw(texRegionToDraw(actionNumber), -WorldController.instance.levelManager.getBg().width/2 +150, 250,0,0,width,height,scale.x,scale.y,rotation);
            else
                batch.draw(texRegionToDraw(actionNumber), WorldController.instance.levelManager.getBg().width/2 -150 -width*scale.x, 250,0,0,width,height,scale.x,scale.y,rotation);

        }

    }
    TextureRegion texRegionToDraw(int i)
    {
        return Assets.getInstance().dice[i];
    }

    @Override
    public void update(float delta) {



        if(isTurn )
        {
            if(inputs.keyEndTurn)
            {
                Gdx.app.debug("PLAYER", "TURN ENDED");
                SoundManager.reproduceSounds(3);
                actionNumber = 0;
                turnPassed = true;
                inputs.keyEndTurn = false;
            }
            if(!isMarkerOnBase())
                    Actions();
        }

    }
    boolean isMarkerOnBase()
    {
        return ((WorldController.instance.levelManager.getMark().row ==0 && WorldController.instance.levelManager.getMark().column ==0) || (WorldController.instance.levelManager.getMark().row ==3 && WorldController.instance.levelManager.getMark().column ==3));
    }

    ArrayList<GameCard> getWorkingCards()
    {
        ArrayList<GameCard>  temp = new ArrayList<GameCard>();
        for(GameWorker gw: gwArray)
        {
            temp.add(gw.gameCard);
        }

        return temp;
    }

    public void Actions()
    {
        if(inputs.keySelectCard)
        {
            Gdx.app.debug("MARK", "Row: " +WorldController.instance.levelManager.getMark().row + " , Column: " + WorldController.instance.levelManager.getMark().column);
            GameCard gc = WorldController.instance.levelManager.getCard(WorldController.instance.levelManager.getMark().row,WorldController.instance.levelManager.getMark().column);

            if(gc.hasWorker())
            {
                //if the worker is one of the current turn's player, accelerate
                if(gc.player==this)
                {
                    Gdx.app.debug("PLAYER", "WORKER ACCELERATED");
                    SoundManager.reproduceSounds(2);
                    actionNumber --;
                    //make card have one less turn too
                    gc.decreaseCardTurn();
                }
                else return;
            }
            else
            {
                //Hacer las movidas de colocar al worker

                for(int i = 0; i < maxWorkers; i++)
                {
                    if(!gwArray[i].working) {
                        gwArray[i].goToCard(gc);
                        gc.workerIndex = i;
                        SoundManager.reproduceSounds(1);
                        actionNumber --;
                        break;
                    }
                }

            }
            inputs.keySelectCard = false;
        }



        if(inputs.keyEndTurn)
        {
            Gdx.app.debug("PLAYER", "TURN ENDED");
            SoundManager.reproduceSounds(3);
            actionNumber = 0;
            turnPassed = true;
            inputs.keyEndTurn = false;
        }
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {

    }
}
