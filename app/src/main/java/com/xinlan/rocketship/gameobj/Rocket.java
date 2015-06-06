package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.xinlan.rocketship.screen.GameScreen;

/**
 * Created by Administrator on 2015/6/6.
 */
public class Rocket extends BaseObject {
    private GameScreen context;
    private TextureRegion rocketTexture;

    private float maxSpeed = 4f;// 最大速度
    protected float mass;//质量

    public Rocket(GameScreen screen) {
        this.context = screen;
        //145
        Texture originTexture = new Texture(Gdx.files.internal("rocket.png"));
        rocketTexture = new TextureRegion(originTexture, 0, 0, 145, 256);
        this.position.set(GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT / 2);
        this.bound.set(this.position.x, this.position.y, 30, 50);
    }

    public void update(float delta) {


        Touchpad touchPad = context.mController.touchpad;
        this.position.add(touchPad.getKnobPercentX() * maxSpeed, touchPad.getKnobPercentY() * maxSpeed);

        //边界条件判断
        if (position.x < this.bound.width / 2) {
            position.x = this.bound.width / 2;
        } else if (position.x > GameScreen.WORLD_WIDTH - this.bound.width / 2) {
            position.x = GameScreen.WORLD_WIDTH - this.bound.width / 2;
        }//end if
        if (position.y < this.bound.height / 2) {
            position.y = this.bound.height / 2;
        } else if (position.y > GameScreen.WORLD_HEIGHT - this.bound.height / 2) {
            position.y = GameScreen.WORLD_HEIGHT - this.bound.height / 2;
        }//end if
    }

    public void render(SpriteBatch batch) {
        batch.draw(rocketTexture, this.position.x - this.bound.width / 2,
                this.position.y - this.bound.height / 2, this.bound.width, this.bound.height);
    }
}//end class
