package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Administrator on 2015/6/9.
 */
public class Stone extends BaseObject {
    public static final int STATUS_NORMAL = 1;
    public int type;//形态
    public int status = STATUS_NORMAL;
    public TextureRegion drawTexture;

    private StoneEngine engine;

    public Stone(StoneEngine engine, float init_x, float init_y, int type) {
        this.engine = engine;
        this.type = type;
        this.position.set(init_x, init_y);
        try {
            drawTexture = StoneFactory.genStone(type, engine);
            bound.set(this.position.x, this.position.y, drawTexture.getRegionWidth(), drawTexture.getRegionHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//end class
