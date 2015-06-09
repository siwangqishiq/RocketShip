package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.xinlan.rocketship.screen.GameScreen;

/**
 * Created by Administrator on 2015/6/6.
 */
public class Rocket extends BaseObject {
    private GameScreen context;
    private TextureRegion rocketTexture;
    private Vector2 force;//计算所受合力
    private float maxSpeed = 400f;// 最大速度

    public Rocket(GameScreen screen) {
        this.context = screen;

        this.mass = 1;
        //145
        Texture originTexture = new Texture(Gdx.files.internal("rocket.png"));
        rocketTexture = new TextureRegion(originTexture, 0, 0, 256, 145);

        this.position.set(GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT / 2);
        this.bound.set(this.position.x, this.position.y, 50, 30);
    }

    public void update(float delta) {
        force = calculateForce();
        Vector2 accelerataion = force.scl(1 / this.mass);//牛顿第二运动定律 计算加速度
        this.velocity.add(accelerataion.scl(delta));//根据加速度 计算当前速度 v=v0+a*t;
        this.velocity.limit(maxSpeed);//限制其最大速度
        this.position.add(this.velocity.cpy().scl(delta));

        heading = this.velocity.cpy().nor();
        siding = this.heading;

        //边界条件判断
        if (position.x < this.bound.width / 2) {
            position.x = this.bound.width / 2;
            velocity.x = 0;
        } else if (position.x > GameScreen.WORLD_WIDTH - this.bound.width / 2) {
            position.x = GameScreen.WORLD_WIDTH - this.bound.width / 2;
            velocity.x = 0;
        }//end if
        if (position.y < this.bound.height / 2) {
            position.y = this.bound.height / 2;
            velocity.y = 0;
        } else if (position.y > GameScreen.WORLD_HEIGHT - this.bound.height / 2) {
            position.y = GameScreen.WORLD_HEIGHT - this.bound.height / 2;
            velocity.y = 0;
        }//end if

    }

    /**
     * 计算物体所受合力
     *
     * @return
     */
    private Vector2 calculateForce() {
        Vector2 retForce = new Vector2(0, 0);
        Touchpad touchPad = context.mController.touchpad;
        float maxForce = 300f;
        retForce.add(touchPad.getKnobPercentX() * maxForce, touchPad.getKnobPercentY() * maxForce);
        return retForce;
    }

    public void render(SpriteBatch batch) {
        batch.draw(rocketTexture, this.position.x - this.bound.width / 2,
                this.position.y - this.bound.height / 2, bound.width / 2, bound.height / 2, bound.width, bound.height, 1, 1, heading.angle());
    }
}//end class
