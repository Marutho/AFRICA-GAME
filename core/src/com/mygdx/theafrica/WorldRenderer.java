package com.mygdx.theafrica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class WorldRenderer {

    public SpriteBatch batch;
    public WorldController controller;
    public String TAG_TIME = "TIMES";
    public float elapsedTime;
    public ShapeRenderer shapeRender;
    public FitViewport viewport;
    public BitmapFont font;

    public WorldRenderer(WorldController wc){
        this.controller = wc;
        init();
    }

    public void init(){
        batch = new SpriteBatch();
        shapeRender = new ShapeRenderer();
        viewport = new FitViewport(1920,1080, controller.ch.camera);
        viewport.apply();
        controller.ch.camera.position.set(0,0,0);
        controller.ch.camera.update();
    }

    public void render(){


        elapsedTime += Gdx.graphics.getDeltaTime();
        long t0 = System.nanoTime();



        //shapeRender.setProjectionMatrix(controller.ch.camera.combined);
        //shapeRender.begin(ShapeRenderer.ShapeType.Line);

        //controller.levelManager.drawDebug(shapeRender);

        //shapeRender.end();
        batch.setProjectionMatrix(controller.ch.camera.combined);
        batch.begin();
        updateArrays();
        batch.end();


        batch.setProjectionMatrix(controller.ch.hudCamera.combined);
        batch.begin();
        controller.hud.render(batch);
        batch.end();


        long elapsed = System.nanoTime() - t0;

        float elapsedMs = elapsed / 1000000;

        //Gdx.app.debug(TAG_TIME, elapsedMs + "ms - " + batch.maxSpritesInBatch + " - " + batch.renderCalls);


    }
    void updateArrays()
    {
        for(int i = 0; i<WorldController.instance.levelManager.Layers.size(); i++)
        {
            for(int j = 0; j<WorldController.instance.levelManager.Layers.get(i).list.size(); j++)
            {
                WorldController.instance.levelManager.Layers.get(i).list.get(j).draw(batch);
            }
        }
    }

    public void resize(int width, int height)
    {
        viewport.update(width,height);
        controller.ch.resize(width,height);
    }
}