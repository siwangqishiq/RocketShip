package com.xinlan.rocketship.gameobj;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator on 2015/6/6.
 */
public class Star {
    public static final int SIZE = 7;
    public Vector2 position = new Vector2(0, 0);
    public float scale = 1f;
    public float maxAlpha = 1f;
    public float minAlpha = 0f;
    public float alpha = 1f;
    public float dAlpha = 0.01f;

    public Star(float x, float y) {
        //System.out.println(x+"    "+y);
        position.set(x, y);
        this.scale = MathUtils.random(0.1f, 1f);
        maxAlpha = 1f;
        minAlpha = 0.2f;
        alpha = MathUtils.random(minAlpha, maxAlpha);
        scale = MathUtils.random(0.1f, 1f);
    }

    public void update() {
        alpha += dAlpha;
        if (alpha > maxAlpha || alpha < minAlpha) {
            dAlpha *= -1;
            alpha += dAlpha;
        }
    }
}//end class
