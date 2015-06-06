package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.xinlan.rocketship.screen.GameScreen;

public class Controller {
    private GameScreen mScreen;
    private Stage stage;
    private Skin touchpadSkin;
    public Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;

    public Controller(GameScreen screen) {
        this.mScreen = screen;

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()),
                screen.batch);
        stage = new Stage();

        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("data/touchBackground.png"));
        touchpadSkin.add("touchKnob", new Texture("data/touchKnob.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = touchpadSkin.getDrawable("touchBackground");
        touchpadStyle.knob = touchpadSkin.getDrawable("touchKnob");

        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(15, 15, 200, 200);

        stage.addActor(touchpad);

        Gdx.input.setInputProcessor(stage);
    }
    public void update(float delta) {
        stage.act(delta);
    }

    public void render(SpriteBatch batch) {
        stage.draw();
    }

    public void dispose(){
        stage.dispose();
    }
}//end class
