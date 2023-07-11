package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Enemy {
    float hp, movespeed, x, y, vx, vy;

    boolean immobile;
    Texture img;
    Enemy(float hp, float movespeed, float x, float y, boolean immobile){
        this.hp = hp;
        this.movespeed = movespeed;
        this.x = x;
        this.y = y;
        this.immobile = immobile;
    }
}
