package com.xinlan.rocketship.gameobj;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Administrator on 2015/6/10.
 */
public class StoneFactory {
    public static TextureRegion genStone(int type,StoneEngine engine) throws Exception {
        TextureRegion drawTexture=null;
        switch (type) {
            case 1:
                drawTexture = engine.stone1Texture;
                break;
            case 2:
                drawTexture = engine.stone2Texture;
                break;
            case 3:
                drawTexture = engine.stone3Texture;
                break;
            case 4:
                drawTexture = engine.stone4Texture;
                break;
            case 5:
                drawTexture = engine.stone5Texture;
                break;
            case 6:
                drawTexture = engine.stone6Texture;
                break;
            case 7:
                drawTexture = engine.stone7Texture;
                break;
            case 8:
                drawTexture = engine.stone8Texture;
                break;
            default:
                throw new Exception("error type");
        }//end switch

        return drawTexture;
    }
}//end class
