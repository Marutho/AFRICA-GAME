package com.mygdx.theafrica;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background extends GameObject {

    public Background(float posX, float posY, float _width, float _height){
        super();
        x=posX;
        y=posY;
        this.width=_width;
        this.height=_height;

        Assets.getInstance().bgWallTR = new TextureRegion(Assets.getInstance().bgWall);

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(Assets.getInstance().bgWallTR,x,y,width,height);
    }

    @Override
    public void draw(SpriteBatch batch, float staTime) {

    }

    @Override
    public void update(float delta) {

    }


}
