package com.mygdx.theafrica.CardUtils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.theafrica.*;

public class GameCard extends GameObject {

    public Vector2 index; //Lo adquiere del index de la casilla de la grid
    public Player player;
    public int usageTurns; //Numero de turnos que le queda a una carta que esta siendo usada
    public Vector2 playerPosition; //Saves the position it shows either player 1 or 2 controls that casilla with the imagen of the jugador
    public Vector2 turnPosition;//Saves the position of the image where the image of the turns that casilla has left
    public CardType ctype;
    boolean isDeleting = false;
    public int workerIndex;

    public GameCard(CardType type, float posX, float posY, int indexRow, int indexColumn)
    {
        x = posX;
        y = posY;
        ctype = type;
        layerTag = Layer.LayerNames.CARD;
        width = 96;
        height = 96;
        scale = new Vector2(2.5f,2.5f);


        updateCardPositions();

        if(ctype == CardType.BANDAGE || ctype ==  CardType.BOOK || ctype == CardType.SEED)
        {
            usageTurns = 6;
        }
        else if (ctype == CardType.WHEAT || ctype == CardType.WOOD || ctype == CardType.IRON)
        {
            usageTurns = 3;
        }

        //playerPosition = new Vector2(x+15, y+15);
        index = new Vector2(indexRow, indexColumn);

    }

    public void decreaseCardTurn()
    {
        usageTurns --;
    }

    void handleExhaustedTurns()
    {
        isDeleting = true;
        SoundManager.reproduceSounds(4);
        WorldController.instance.levelManager.getGrid().replaceCard((int)index.x, (int)index.y);
        player.gwArray[workerIndex].getBackToBase();
        giveRessourceToPlayer();
        player = null;
        WorldController.instance.levelManager.Despawn(this);

    }

    void giveRessourceToPlayer()
    {
        switch (ctype)
        {
            case WHEAT: player.currentWheat++;
            break;
            case WOOD: player.currentWood++;
                break;
            case IRON: player.currentIron++;
                break;
            case BOOK: player.currentBooks++;
                break;
            case BANDAGE: player.currentBandages++;
                break;
            case SEED: player.currentSeeds++;
                break;

        }
    }


    public void updateCardPositions()
    {
        playerPosition = new Vector2(x+75*scale.x, y+3*scale.y);
        turnPosition = new Vector2(x+76*scale.x, y+76*scale.y);
    }

    public boolean hasWorker()
    {
        return(player != null);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texRegionToDraw(ctype.getValue()),x,y,0,0,width,height,scale.x,scale.y,rotation);
        if(usageTurns>=0)
        batch.draw(texRegionToDrawDICE(usageTurns), turnPosition.x, turnPosition.y,0,0,15,15,scale.x,scale.y,rotation);
    }
    TextureRegion texRegionToDraw(int i)
    {
        return Assets.getInstance().cardsReg[i];
    }
    TextureRegion texRegionToDrawDICE(int i)
    {
        return Assets.getInstance().dice[i];
    }

    @Override
    public void update(float delta) {

        if(usageTurns <=0 && !isDeleting)
        {
            handleExhaustedTurns();
        }

    }


    @Override
    public void draw(SpriteBatch batch, float staTime) {

    }
}
