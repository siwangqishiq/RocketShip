package com.xinlan.rocketship.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.xinlan.rocketship.RocketApp;
import com.xinlan.rocketship.gameobj.Controller;
import com.xinlan.rocketship.gameobj.Rocket;
import com.xinlan.rocketship.gameobj.StoneEngine;
import com.xinlan.rocketship.gameobj.Universal;

/**
 */
public final class GameScreen extends DefautScreen {
    public static final int WORLD_WIDTH = 6000;//世界宽
    public static final int WORLD_HEIGHT = 3000;//世界高

    public static final int SCREEN_HEIGHT = 480;
    public static final int SCREEN_WIDTH = 800;

    public OrthographicCamera gameCamera;
    public SpriteBatch batch;

    public OrthographicCamera backgroundCamera;
    public SpriteBatch backgroundBatch;

    //GameObject
    public Controller mController;
    Universal mUniversal;
    Rocket mRocket;
    StoneEngine mStoneEngine;

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
        mController = new Controller(this);

        mUniversal = new Universal(this);

        mRocket = new Rocket(this);
        mStoneEngine = new StoneEngine(this);

        mUniversal.initStars();
        mStoneEngine.initStones();
    }

    @Override
    public void render(float delta) {
        //TODO update
        mController.update(delta);
        mUniversal.update(delta);
        mRocket.update(delta);
        mStoneEngine.update(delta);

        //TODO render
        delta = Math.min(0.06f, delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);//刷新黑色背景
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateGameCamera();//更新摄像机位置

        batch.begin();
        mUniversal.render(batch);
        mRocket.render(batch);
        mStoneEngine.render(batch);
        batch.end();

        mController.render(batch);
    }

    private void updateGameCamera() {
        batch.setProjectionMatrix(gameCamera.combined);
        //摄像机跟随
        Vector3 cameraPos = gameCamera.position;
        cameraPos.x = mRocket.position.x;

        if (cameraPos.x + SCREEN_WIDTH / 2 > WORLD_WIDTH) {
            cameraPos.x = WORLD_WIDTH - SCREEN_WIDTH / 2;
        } else if (cameraPos.x < SCREEN_WIDTH / 2) {
            cameraPos.x = SCREEN_WIDTH / 2;
        }

        cameraPos.y = mRocket.position.y;
        if (gameCamera.position.y < SCREEN_HEIGHT / 2) {
            cameraPos.y = SCREEN_HEIGHT / 2;
        } else if (gameCamera.position.y > WORLD_HEIGHT - SCREEN_HEIGHT / 2) {
            cameraPos.y = WORLD_HEIGHT - SCREEN_HEIGHT / 2;
        }//end if

        gameCamera.update();
    }


    @Override
    public void dispose() {
        mController.dispose();
        mUniversal.dispose();
        mRocket.dispose();
        mStoneEngine.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }
}//end class
