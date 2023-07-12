package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends Sprite {
    public float hp;
    float x;
    float y;

    boolean immobile;
    Texture img;
    Enemy(Texture img, float hp, float x, float y, boolean immobile){
        super(img, 0, 0, img.getWidth(), img.getHeight());
        this.hp = hp;

        this.x = x;
        this.y = y;
        this.immobile = immobile;
    }
}
