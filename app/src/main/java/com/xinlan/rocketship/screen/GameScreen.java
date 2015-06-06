package com.xinlan.rocketship.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xinlan.rocketship.RocketApp;
import com.xinlan.rocketship.gameobj.Universal;

/**
 */
public final class GameScreen extends DefautScreen {
    public static final int WORLD_WIDTH = 2000;//世界宽
    public static final int WORLD_HEIGHT = 2000;//世界高

    public static final int SCREEN_HEIGHT = 480;
    public static final int SCREEN_WIDTH = 800;

    public OrthographicCamera gameCamera;
    public SpriteBatch batch;

    public OrthographicCamera backgroundCamera;
    public SpriteBatch backgroundBatch;

    //GameObject
    Universal mUniversal;

    public GameScreen(RocketApp game) {
        super(game);
    }

    @Override
    public void show() {
        gameCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        gameCamera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        batch = new SpriteBatch();



        gameInit();
    }

    private void gameInit() {
        mUniversal = new Universal(this);
        mUniversal.initStars();
    }

    @Override
    public void render(float delta) {
        //TODO update
        mUniversal.update(delta);

        //TODO render
        delta = Math.min(0.06f, delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);//刷新黑色背景
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(gameCamera.combined);
        gameCamera.update();
        batch.begin();
        mUniversal.render(batch);
        batch.end();
    }


    @Override
    public void dispose() {
        mUniversal.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }
}//end class
