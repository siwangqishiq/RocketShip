package com.xinlan.rocketship.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.xinlan.rocketship.RocketApp;

/**
 */
public class GameScreen extends DefautScreen {
    public static final int WORLD_WIDTH = 100000;//世界宽
    public static final int WORLD_HEIGHT = 100000;//世界高

    public static final int SCREEN_HEIGHT = 480;
    public static final int SCREEN_WIDTH = 800;

    public OrthographicCamera gameCamera;

    public GameScreen(RocketApp game) {
        super(game);
    }

    @Override
    public void show() {
        gameCamera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);

    }

    @Override
    public void render(float delta) {
        delta = Math.min(0.06f, delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glIsEnabled(GL20.GL_BLEND);

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {

    }
}//end class
