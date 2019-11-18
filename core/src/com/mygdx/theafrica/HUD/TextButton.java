package com.mygdx.theafrica.HUD;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.theafrica.Assets;

public class TextButton extends HUDElement {

    private final float textY;
    private final float textX;
    String text;
    GlyphLayout glyphLayout;
    Rectangle bounds;

    public TextButton(String text,float x,float y,float width,float height){
        this.text = text;
        this.dimension = new Vector2(width,height);
        this.position = new Vector2(x,y);
        glyphLayout = new GlyphLayout(Assets.getInstance().font, text);
        textX = position.x + (dimension.x-glyphLayout.width)/2;
        textY = position.y + dimension.y - (dimension.y-glyphLayout.height)/2;
        bounds = new Rectangle(position.x,position.y,dimension.x,dimension.y);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().button,position.x,position.y,dimension.x,dimension.y);
        Assets.getInstance().font.draw(batch,text,textX,textY);
    }

    public boolean contains(float x, float y){
        return bounds.contains(x,y);
    }

    public void click(){
        System.out.println("You clicked the "+text+" button");
    }
}

