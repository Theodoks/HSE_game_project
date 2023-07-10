package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EggChild extends Sprite {
    float x, y;
    EggChild(Texture childTexture, float x, float y, float width, float height){
        super(childTexture, 0, 0, childTexture.getWidth(), childTexture.getHeight() );
        this.x = x;
        this.y = y;
        setPosition(x, y);
        setSize(width, height);
    }
}
