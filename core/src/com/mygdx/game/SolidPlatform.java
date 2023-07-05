package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class SolidPlatform {
    Texture plat;
    int x, y;
    float width, height;
    SolidPlatform(Texture plat, int x, int y, float width, float height){
        this.plat = plat;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void exist(){
        MyGdxGame.batch.draw(plat, x, y, width, height);
    }
}
