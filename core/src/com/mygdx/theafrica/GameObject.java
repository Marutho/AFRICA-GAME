package com.mygdx.theafrica;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

abstract public class GameObject {

   // public Vector2 position;
    public float x, y;
    public float rotation;
    public Vector2 scale = new Vector2(1,1);
    public float width, height;
    public Layer.LayerNames layerTag;
    public Rectangle rectangle;
    LevelManager lvl;

    public GameObject()
    {
        x=0;
        y=0;
        width=1;
        height=1;
        rectangle=new Rectangle();
    }

    public GameObject(LevelManager levelManager)
    {
        lvl= levelManager;
        x=0;
        y=0;
        width=1;
        height=1;
        rectangle=new Rectangle();
    }

    public GameObject(float x,float y,float width,float height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        rectangle=new Rectangle();
    }

    public void drawDebug(ShapeRenderer shape)
    {
        shape.setColor(Color.RED);
        shape.rect(x,y,width,height);
    }

    public Vector2 getCenterPos()
    {
        return new Vector2(x+ width/2,y+ height/2);
    }

    public Rectangle GetRectangle(){
        return (rotation == 0 ? rectangle.set(x,y,width,height):rectangle.set(x-width,y-height,width,height));//returns normal or inverted rectangle based on the rotation of the gameobject (needs improvement)s
    }

    public Rectangle getBounds(){
        rectangle.set(x,y,width,height);
        return rectangle;
    }

    abstract public void draw(SpriteBatch batch);
    abstract public void update(float delta);
    abstract public void draw(SpriteBatch batch, float staTime);
}
