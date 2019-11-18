package com.mygdx.theafrica;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;


public class SCREEN_MAIN implements Screen {

	String TAG_LIFECYCLE = "LIFECYCLE";
	public WorldController controller;
	public WorldRenderer renderer;

	public MAIN_GAME gameMain;
	public LevelManager levelManager;
	public int numPlayers;


	public SCREEN_MAIN(MAIN_GAME gameMain) {
		this.gameMain = gameMain;
	}

	@Override
	public void show() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug(TAG_LIFECYCLE, "Created");

		controller = new WorldController();

		renderer = new WorldRenderer(controller);

	}

	@Override
	public void render(float delta) {
		controller.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
		//iProcessor.Update();

	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width,height);
		Gdx.app.debug(TAG_LIFECYCLE, "Resized to: "+ height + "x" + width);
		controller.ch.hudCamera.viewportWidth = width;
		controller.ch.hudCamera.viewportHeight = height;
		//0,0 in the lower left corner
		controller.ch.hudCamera.position.x = width/2;
		controller.ch.hudCamera.position.y = height/4;
		controller.ch.hudCamera.update();


	}

	@Override
	public void pause() {
		Gdx.app.debug(TAG_LIFECYCLE, "Paused");
	}

	@Override
	public void resume() {
		Gdx.app.debug(TAG_LIFECYCLE, "Resumed");
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		Gdx.app.debug(TAG_LIFECYCLE, "Disposed");
	}
}
