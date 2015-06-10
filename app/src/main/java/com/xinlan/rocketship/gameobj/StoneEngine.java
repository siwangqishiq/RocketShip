package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.xinlan.rocketship.screen.GameScreen;

/**
 * Created by Administrator on 2015/6/9.
 */
public class StoneEngine {
    public static final int MAX_TYPE = 8;
    public static final int MAX_STONE_NUM = 200;

    public TextureRegion stone1Texture, stone2Texture, stone3Texture, stone4Texture,
            stone5Texture, stone6Texture, stone7Texture, stone8Texture;

    private GameScreen context;

    private Array<Stone> stoneList = new Array<Stone>();

    public StoneEngine(GameScreen mContext) {
        this.context = mContext;
        loadResource();
    }

    public void initStones() {
        stoneList.clear();
        for (int i = 0; i < MAX_STONE_NUM; i++) {
            int stoneType = MathUtils.random(1, MAX_TYPE);
            //System.out.println("type-->" + stoneType);
            Stone stone = new Stone(this, MathUtils.random(0, GameScreen.WORLD_WIDTH),
                    MathUtils.random(0, GameScreen.WORLD_HEIGHT), stoneType);
            stoneList.add(stone);
        }//end for i
    }

    public void update(float delta) {
        for (Stone stone : stoneList) {
            int dx = MathUtils.random(0,1);
            int dy = MathUtils.random(-1,1);
            stone.position.add(dx,dy);
            stone.bound.setPosition(stone.position);


        }//end for each
    }

    public void render(SpriteBatch batch) {
        TextureRegion drawTexture = null;
        for (Stone stone : stoneList) {
            batch.draw(stone.drawTexture, stone.position.x, stone.position.y,
                    stone.bound.width, stone.bound.height);
        }//end for each
    }

    public void dispose() {

    }

    /**
     * 载入资源文件
     */
    private void loadResource() {
        Texture texture;
        texture = new Texture(Gdx.files.internal("stone1.png"));
        stone1Texture = new TextureRegion(texture, 0, 0, 90, 128);

        texture = new Texture(Gdx.files.internal("stone2.png"));
        stone2Texture = new TextureRegion(texture, 0, 0, 128, 91);

        texture = new Texture(Gdx.files.internal("stone3.png"));
        stone3Texture = new TextureRegion(texture, 0, 0, 111, 62);

        texture = new Texture(Gdx.files.internal("stone4.png"));
        stone4Texture = new TextureRegion(texture, 0, 0, 128, 95);

        texture = new Texture(Gdx.files.internal("stone5.png"));
        stone5Texture = new TextureRegion(texture, 0, 0, 128, 93);

        texture = new Texture(Gdx.files.internal("stone6.png"));
        stone6Texture = new TextureRegion(texture, 0, 0, 128, 73);

        texture = new Texture(Gdx.files.internal("stone7.png"));
        stone7Texture = new TextureRegion(texture, 0, 0, 128, 128);

        texture = new Texture(Gdx.files.internal("stone8.png"));
        stone8Texture = new TextureRegion(texture, 0, 0, 128, 92);
    }
}//end class
