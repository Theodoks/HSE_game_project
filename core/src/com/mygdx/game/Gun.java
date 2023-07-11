package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Gun extends Sprite {
    float x, y;
    boolean bodyRotation = true;
    Gun(Texture gunTexture, float x, float y, float width, float height){
        super(gunTexture, 0, 0, gunTexture.getWidth(), gunTexture.getHeight() );
        this.x = x;
        this.y = y;
        setPosition(x, y);
        setSize(width, height);

    }
    void update(float x, float y, boolean bodyRotation){
        this.x = x;
        this.y = y;
        setPosition(x, y);
        if(bodyRotation != this.bodyRotation){
            flip(true, false);
            this.bodyRotation = bodyRotation;
        }
    }
}
