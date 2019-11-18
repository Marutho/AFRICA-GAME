package com.mygdx.theafrica;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.mygdx.theafrica.CardUtils.GameCard;

import java.util.concurrent.TimeUnit;

public class TurnManager extends GameObject {

    Player p1, p2;
    int turnsToGive;
    float padding = 150;
    float paddingResources=20;
    public BitmapFont font;

    Player activePlayer;
    public static TurnManager instance;

    public TurnManager()
    {
        if(TurnManager.instance ==null)
        {
            instance = this;
        }
        else if(TurnManager.instance != this)
        {
            WorldController.instance = null;
        }

        font = new BitmapFont();
        p1 = null;
        p2 = null;
        activePlayer = null;
        layerTag = Layer.LayerNames.DEFAULT;
    }

    //THE FORMULA FOR RANDOMS IN A RANGE IS: (Math.random() * ((max - min) + 1)) + min;
    public void giveActions(Player p){
       p.actionNumber= (int) ((Math.random()*100*5)/100 +2);

    }

    public void chooseInitialTurn(){
        int randomNum=(int) ((Math.random()*100*2)/100);
        if(randomNum==1)
        {
            p1.actionNumber = (int) ((Math.random()*100*5)/100 +2);
            p1.isTurn = true;
            p2.isTurn = false;
            activePlayer = p1;
            WorldController.instance.levelManager.getMark().setMarkerOnPlayer(1);
        }
        else
        {
            p2.actionNumber = (int) ((Math.random()*100*5)/100 +2);
            p2.isTurn = true;
            p1.isTurn = false;
            activePlayer = p2;
            WorldController.instance.levelManager.getMark().setMarkerOnPlayer(2);


        }
    }


    @Override
    public void update(float delta)
    {

        if(p1 == null && p2 == null) {
            p1 = WorldController.instance.levelManager.getPlayer(1);
            p2 = WorldController.instance.levelManager.getPlayer(2);
            chooseInitialTurn();
        }

        if(p1.buildingsNumber>=2)
        {

            if(WorldController.instance.inputMgr.keySelectCard)
            {MAIN_GAME.instance.setScreen(MAIN_GAME.instance.menuScreen);}
        }
        else if(p2.buildingsNumber>=2)
        {

            if(WorldController.instance.inputMgr.keySelectCard)
            {MAIN_GAME.instance.setScreen(MAIN_GAME.instance.menuScreen);}
        }

        if(p1.isTurn==true)
        {
            if(p1.actionNumber<=0 || p1.turnPassed)
            {

                p1.turnPassed = false;
                p1.isTurn = false;
                if(p1.getWorkingCards().size()!=0)
                {
                    for(GameCard gc : p1.getWorkingCards())
                    {
                        if(gc!=null)
                        gc.decreaseCardTurn();
                    }
                }
                giveActions(p2);
                p2.isTurn = true;
                activePlayer = p2;
                WorldController.instance.levelManager.getMark().setMarkerOnPlayer(2);

            }
        }

        else if(p2.isTurn==true)
        {
            if(p2.actionNumber<=0 || p2.turnPassed)
            {
                p2.turnPassed = false;
                p2.isTurn=false;
                if(p2.getWorkingCards().size()!=0)
                {
                    for(GameCard gc : p2.getWorkingCards())
                    {
                        if(gc!=null)
                        gc.decreaseCardTurn();
                    }
                }
                giveActions(p1);
                p1.isTurn=true;
                activePlayer = p1;
                WorldController.instance.levelManager.getMark().setMarkerOnPlayer(1);

            }
        }
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        if(p1!=null && p2 != null)
        {

            //WIN LOGIC
            if(p1.buildingsNumber>=2)
            {
                font.draw(batch,"PLAYER 1 WON!",-500,-500,1000, Align.center,true);

            }
            else if(p2.buildingsNumber>=2)
            {
                font.draw(batch,"PLAYER 2 WON!",0,0,102, Align.center,true);

            }

            if(p1.isTurn)
            {
                batch.draw(Assets.getInstance().turnsTR[0], -WorldController.instance.levelManager.getBg().width/2 + padding ,400,0,0,102,50,2.5f,2.5f,0);
            }

            if(p2.isTurn)
            {
                batch.draw(Assets.getInstance().turnsTR[1], WorldController.instance.levelManager.getBg().width/2 - padding -102*2.5f,400,0,0,102,50,2.5f,2.5f,0);
            }
            batch.draw(Assets.getInstance().greenhouse,-WorldController.instance.levelManager.getBg().width/2 +padding,+paddingResources*10,15*scale.x,15*scale.y);
            font.draw(batch,"BUILDINGS PLAYER 1",-WorldController.instance.levelManager.getBg().width/2 +padding,+paddingResources*10,200, Align.center,true);
            font.draw(batch,"- HOSPITAL: IRON: "+p1.currentIron+"/5 WHEATS: "+p1.currentWheat+"/5 BANDAGES: "+p1.currentBandages+"/2",-WorldController.instance.levelManager.getBg().width/2 +padding/2,+paddingResources*8,400, Align.left,true);
            font.draw(batch,"- SCHOOL: WOOD: "+p1.currentWood+"/5 METAL: "+p1.currentIron+"/5 BOOKS: "+p1.currentBooks+"/2",-WorldController.instance.levelManager.getBg().width/2 +padding/2,+paddingResources*7,400, Align.left,true);
            font.draw(batch,"- GREENHOUSE: WOOD: "+p1.currentWood+"/5 WHEATS: "+p1.currentWheat+"/5 SEEDS: "+p1.currentSeeds+"/2",-WorldController.instance.levelManager.getBg().width/2 +padding/2,+paddingResources*6,400, Align.left,true);
            font.draw(batch,"RESOURCES PLAYER 1",-WorldController.instance.levelManager.getBg().width/2 +padding,0,102, Align.center,true);
            font.draw(batch,"- Wood: "+p1.currentWood,-WorldController.instance.levelManager.getBg().width/2 +padding,-paddingResources*2,102, Align.left,true);
            font.draw(batch,"- Iron: "+p1.currentIron,-WorldController.instance.levelManager.getBg().width/2 +padding,-paddingResources*3,102, Align.left,true);
            font.draw(batch,"- Seeds: "+p1.currentWheat,-WorldController.instance.levelManager.getBg().width/2 +padding,-paddingResources*4,102, Align.left,true);
            font.draw(batch,"- Books: "+p1.currentBooks,-WorldController.instance.levelManager.getBg().width/2 +padding,-paddingResources*5,102, Align.left,true);
            font.draw(batch,"- Bandages: "+p1.currentSeeds,-WorldController.instance.levelManager.getBg().width/2 +padding,-paddingResources*6,102, Align.left,true);
            font.draw(batch,"- Seeds: "+p1.currentBandages,-WorldController.instance.levelManager.getBg().width/2 +padding,-paddingResources*7,102, Align.left,true);

            //PLAYER 2
            font.draw(batch,"BUILDINGS PLAYER 2",WorldController.instance.levelManager.getBg().width/2 -padding*2,+paddingResources*10,200, Align.center,true);
            font.draw(batch,"- HOSPITAL: IRON: "+p2.currentIron+"/5 WHEATS: "+p2.currentWheat+"/5 BANDAGES: "+p2.currentBandages+"/2",WorldController.instance.levelManager.getBg().width/2 -padding*2.5f,+paddingResources*8,400, Align.left,true);
            font.draw(batch,"- SCHOOL: WOOD: "+p2.currentWood+"/5 METAL: "+p2.currentIron+"/5 BOOKS: "+p2.currentBooks+"/2",WorldController.instance.levelManager.getBg().width/2 -padding*2.5f,+paddingResources*7,400, Align.left,true);
            font.draw(batch,"- GREENHOUSE: WOOD: "+p2.currentWood+"/5 WHEATS: "+p2.currentWheat+"/5 SEEDS: "+p2.currentSeeds+"/2",WorldController.instance.levelManager.getBg().width/2 -padding*2.5f,+paddingResources*6,400, Align.left,true);
            font.draw(batch,"RESOURCES PLAYER 2",WorldController.instance.levelManager.getBg().width/2 -padding*2,0,102, Align.center,true);
            font.draw(batch,"- Wood: "+p2.currentWood,WorldController.instance.levelManager.getBg().width/2 -padding*2,-paddingResources*2,102, Align.left,true);
            font.draw(batch,"- Iron: "+p2.currentIron,WorldController.instance.levelManager.getBg().width/2 -padding*2,-paddingResources*3,102, Align.left,true);
            font.draw(batch,"- Seeds: "+p2.currentWheat,WorldController.instance.levelManager.getBg().width/2 -padding*2,-paddingResources*4,102, Align.left,true);
            font.draw(batch,"- Books: "+p2.currentBooks,WorldController.instance.levelManager.getBg().width/2 -padding*2,-paddingResources*5,102, Align.left,true);
            font.draw(batch,"- Bandages: "+p2.currentSeeds,WorldController.instance.levelManager.getBg().width/2 -padding*2,-paddingResources*6,102, Align.left,true);
            font.draw(batch,"- Seeds: "+p2.currentBandages,WorldController.instance.levelManager.getBg().width/2 -padding*2,-paddingResources*7,102, Align.left,true);

        }//MADE BY THE BROUDERS
    }
}

