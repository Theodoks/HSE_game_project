package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
public class GrassyPlat extends SolidPlatform{

    public GrassyPlat(float x, float y, float width, float height){
        super(x, y);
        this.width = width;
        this.height = height;
        img = new Texture("Terrain2.PNG");
    }

}
