package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {
    float x, y;
    float vx, vy;
    float width, height;

    Player(Texture img, int srcx, int srcy, int srcwidth, int srcheight, float width, float height,
           float x, float y, float vx, float vy) {
        super(img, srcx, srcy, srcwidth, srcheight);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        setSize(width, height);
        setPosition(x, y);
    }
}
