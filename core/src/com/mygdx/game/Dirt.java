package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Dirt extends SolidPlatform{
    public Dirt(float x, float y, float width, float height){
        super(x, y);
        this.width = width;
        this.height = height;
        img = new Texture("dirt.png");
    }


}
