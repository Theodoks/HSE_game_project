package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends Sprite {
    float hp, moveSpeed, x, y, vx, vy;

    boolean immobile;
    Texture img;
    Enemy(Texture img, float hp, float moveSpeed, float x, float y, boolean immobile){
        super(img, 0, 0, img.getWidth(), img.getHeight());
        this.hp = hp;
        this.moveSpeed = moveSpeed;
        this.x = x;
        this.y = y;
        this.immobile = immobile;
    }
}
