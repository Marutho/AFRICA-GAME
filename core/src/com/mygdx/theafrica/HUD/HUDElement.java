package com.mygdx.theafrica.HUD;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class HUDElement {
    Vector2 position;
    Vector2 dimension;

    public abstract void render(SpriteBatch batch);
}
