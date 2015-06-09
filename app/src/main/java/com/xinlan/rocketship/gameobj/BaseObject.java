package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator
 * <p/>
 * on 2015/6/6.
 */
public abstract class BaseObject {
    public Vector2 position = new Vector2();
    public Rectangle bound = new Rectangle();
    public Vector2 heading = new Vector2();
    public Vector2 siding = new Vector2();
    public Vector2 velocity = new Vector2();//当前速度
    public float headAngle = 0;//朝向角度
    public float mass = 1;//质量
}//end class
