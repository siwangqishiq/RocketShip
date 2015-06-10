package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Array;
import com.xinlan.rocketship.screen.GameScreen;

/**
 * Created by Administrator on 2015/6/6.
 */
public class Rocket extends BaseObject {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 30;

    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_EXPLOATION = 2;
    public static final int STATUS_DEAD = 3;

    private GameScreen context;
    private TextureRegion rocketTexture;
    private Vector2 force;//计算所受合力
    private float maxSpeed = 320f;// 最大速度
    public static final float maxForce = 700f;//飞船制动力

    private float deadTimeCount = 0;
    private static final float DEAD_WAIT_TIME = 2.0f;
    private ParticleEffect particle;

    private int status = STATUS_NORMAL;

    public Rocket(GameScreen screen) {
        this.context = screen;

        this.mass = 0.5f;
        //145
        Texture originTexture = new Texture(Gdx.files.internal("rocket.png"));
        rocketTexture = new TextureRegion(originTexture, 0, 0, 256, 145);

        particle = new ParticleEffect();
        particle.load(Gdx.files.internal("particle/rocket.p"), Gdx.files.internal("particle/"));
        particle.getEmitters().first().setContinuous(true);

        //init();
    }

    public void init() {
        status = STATUS_NORMAL;
        deadTimeCount = 0;
        velocity.set(0, 0);
        this.heading.set(0, 0);
        this.position.set(GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT / 2);
        this.bound.set(this.position.x, this.position.y, WIDTH, HEIGHT);

        Vector2 rectCenter = new Vector2();
        bound.getCenter(rectCenter);
        boundCircle.set(rectCenter.x, rectCenter.y, bound.width / 2 - 5);
        //粒子效果载入
        particle.setPosition(position.x - bound.width, position.y);
        particle.reset();
        particle.start();
    }

    /**
     * status = STATUS_NORMAL;
     * deadTimeCount = 0;
     * velocity.set(0, 0);
     * this.heading.set(0, 0);
     * this.position.set(GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT / 2);
     * this.bound.set(this.position.x, this.position.y,
     * rocketTexture.getRegionWidth(), rocketTexture.getRegionHeight());
     * <p/>
     * //粒子效果载入
     * particle.setPosition(position.x - bound.width, position.y);
     * particle.reset();
     * particle.start();
     *
     * @param delta
     */

    public void update(float delta) {
        switch (status) {
            case STATUS_NORMAL:
                normalUpdate(delta);
                break;
            case STATUS_EXPLOATION:
                deadTimeCount += delta;
                if (deadTimeCount >= DEAD_WAIT_TIME) {
                    this.status = STATUS_DEAD;
                }
                break;
            case STATUS_DEAD:
                //game over restart
                context.gameInit();
                break;
        }

        updateParticle(delta);  //粒子系统更新
    }

    /**
     * 更新粒子系统
     *
     * @param delta
     */
    private void updateParticle(float delta) {
        particle.setPosition(position.x, position.y);
        rotateBy(heading.angle() + 180);
        particle.update(delta);
        if (particle.isComplete())
            particle.reset();

        if (this.velocity.len() < 50) {
            particle.allowCompletion();
        }
    }

    private void normalUpdate(float delta) {
        force = calculateForce();
        Vector2 accelerataion = force.scl(1 / this.mass);//牛顿第二运动定律 计算加速度
        this.velocity.add(accelerataion.scl(delta));//根据加速度 计算当前速度 v=v0+a*t;
        this.velocity.limit(maxSpeed);//限制其最大速度
        this.position.add(this.velocity.cpy().scl(delta));
        bound.setPosition(position);
        boundCircle.setPosition(position);
        heading = this.velocity.cpy().nor();
        siding = this.heading;

        //碰撞检测
        collisonCheck();

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

    public void render(SpriteBatch batch) {

        if (status == STATUS_NORMAL) {
            particle.draw(batch);
            batch.draw(rocketTexture, this.position.x - this.bound.width / 2,
                    this.position.y - this.bound.height / 2, bound.width / 2,
                    bound.height / 2, bound.width, bound.height, 1, 1, heading.angle());
            return;
        }

        if (status == STATUS_EXPLOATION) {
            //show Explosion

        }
//        batch.draw(rocketTexture, this.position.x - this.bound.width / 2,
//                this.position.y - this.bound.height / 2, bound.width / 2, bound.height / 2, bound.width, bound.height, 1, 1, heading.angle());
    }

    /**
     * 碰撞检测
     */
    private void collisonCheck() {
        Array<Stone> stones = context.mStoneEngine.stoneList;
        for (Stone stone : stones) {
            if (Intersector.overlaps(this.boundCircle, stone.bound)) {
                this.status = STATUS_EXPLOATION;
                deadTimeCount = 0;
                //particle.allowCompletion();
                //particle.reset();
                break;
            }
        }//end for each
    }

    public void rotateBy(float amountInDegrees) {
        Array<ParticleEmitter> emitters = particle.getEmitters();
        for (int i = 0; i < emitters.size; i++) {
            ParticleEmitter.ScaledNumericValue val = emitters.get(i).getAngle();
            float amplitude = (val.getHighMax() - val.getHighMin()) / 2f;
            float h1 = amountInDegrees + amplitude;
            float h2 = amountInDegrees - amplitude;
            val.setHigh(h1, h2);
            val.setLow(amountInDegrees);
        }//end for i
    }

    /**
     * 计算物体所受合力
     *
     * @return
     */
    private Vector2 calculateForce() {
        Vector2 retForce = new Vector2(0, 0);
        Touchpad touchPad = context.mController.touchpad;
        retForce.add(touchPad.getKnobPercentX() * maxForce, touchPad.getKnobPercentY() * maxForce);
        return retForce;
    }

    public void dispose() {
        particle.dispose();
    }
}//end class
