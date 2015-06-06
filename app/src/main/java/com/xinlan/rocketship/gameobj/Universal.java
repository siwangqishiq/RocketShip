package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.xinlan.rocketship.screen.GameScreen;


/**
 * Created by Administrator on 2015/6/6.
 */
public class Universal {
    private Array<Star> stars = new Array<Star>();
    private Texture starTexture;

    public Universal(GameScreen screen) {
        starTexture = new Texture(Gdx.files.internal("stars.png"));
    }

    public void initStars() {
        final int step = 70;
        for (int i = 0; i < GameScreen.WORLD_HEIGHT; i += step) {
            for (int j = 0; j < GameScreen.WORLD_WIDTH; j += step) {
                stars.add(new Star(MathUtils.random(j, j + step), MathUtils.random(i, i + step)));
            }//end for j
        }//end for i
    }

    public void update(float delta) {
        for (Star star : stars) {
            star.update();
        }//end for each
    }

    public void render(SpriteBatch batch) {
        final Color originColor = batch.getColor();
        for (Star star : stars) {
            batch.setColor(originColor.r, originColor.g, originColor.b, star.alpha);
            batch.draw(starTexture, star.position.x,
                    star.position.y, Star.SIZE * star.scale, Star.SIZE *  star.scale);
        }//end for each
        batch.setColor(originColor);
    }

    public void dispose() {

    }
}//end class
