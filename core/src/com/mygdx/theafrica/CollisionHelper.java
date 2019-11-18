package com.mygdx.theafrica;

public class CollisionHelper {

    public static boolean CheckCollision(GameObject g1, GameObject g2)
    {
        return g1.GetRectangle().overlaps(g2.GetRectangle());
    }
}
