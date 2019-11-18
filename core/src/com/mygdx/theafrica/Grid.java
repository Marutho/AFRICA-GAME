package com.mygdx.theafrica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.theafrica.CardUtils.CardType;
import com.mygdx.theafrica.CardUtils.GameCard;
import com.mygdx.theafrica.CardUtils.PlayerBase;

public class Grid extends GameObject{

    int size;
    int cardSize;
    int originalX;
    int originalY;

    public Vector2[][] positions;
    public boolean[][] hasCard;
    public float probability = 15f;

    public Grid()
    {
        size = 4;

        positions = new Vector2[size][size];
        hasCard = new boolean[size][size];

        layerTag = Layer.LayerNames.BACKGROUND;
        cardSize = (int)(96 * 2.5f);
        originalX = - size/2*cardSize;
        originalY = - cardSize;
        int index = 0;

        Player player1;
        Player player2;

        for(int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {

                positions[i][j] = new Vector2();
                positions[i][j].x = originalX + cardSize * j;
                positions[i][j].y = -originalY - cardSize * i;

                String name = null;
                if(i==0 && j==0)
                {
                    name = "player 1";
                    player1 = new Player(1, positions[i][j].x, positions[i][j].y);
                    WorldController.instance.levelManager.Instantiate(player1);
                }
                else if(i==size-1 && j==size-1)
                {
                    name = "player 2";
                    player2 = new Player(2, positions[i][j].x, positions[i][j].y);
                    WorldController.instance.levelManager.Instantiate(player2);
                }
                else if((i!=0 && j!=0) || (i!=size && j!=size))
                {
                    name = "Normal card";
                    WorldController.instance.levelManager.Instantiate(new GameCard(GetCardType(), positions[i][j].x, positions[i][j].y, i, j));
                }

                hasCard[i][j] = true;

                Gdx.app.debug("GRID","Position: " + positions[i][j].x + ", "+ positions[i][j].y + ", Index: " + index + ", Name: "+ name);
                index ++;
            }
        }

    }

    public void replaceCard(int row, int column)
    {
        WorldController.instance.levelManager.Instantiate(new GameCard(GetCardType(), positions[row][column].x, positions[row][column].y, row, column));
    }

    CardType GetCardType()
    {
        float randomizer = (float)Math.random()*100;
        if(randomizer >= probability)
        {
            int randomCard = (int)(Math.random()*100 * 3 )/100;// lol esto funciona
            switch(randomCard)
            {
                case 0:
                    return CardType.WHEAT;

                case 1:
                    return CardType.WOOD;

                case 2:
                    return CardType.IRON;

                default:
                    return CardType.WHEAT;
            }
        }
        else
        {
            int randomCard = (int)(Math.random()*100 * 3 )/100;
            switch(randomCard)
            {
                case 0:
                    return CardType.BOOK;

                case 1:
                    return CardType.BANDAGE;

                case 2:
                    return CardType.SEED;

                default:
                    return CardType.BOOK;
            }
        }
    }

    @Override
    public void update(float delta) {


    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {

    }
}
