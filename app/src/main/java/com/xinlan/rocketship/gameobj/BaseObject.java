package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator
 *
 * on 2015/6/6.
 */
public abstract class BaseObject {
    public Vector2 position = new Vector2();
    public Vector2 maxVelocity = new Vector2();
    public Vector2 heading = new Vector2();
    public Vector2 siding = new Vector2();
    public Rectangle bound = new Rectangle();
}//end class
