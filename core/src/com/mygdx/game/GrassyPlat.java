package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class GrassyPlat extends SolidPlatform{

    public GrassyPlat(float x, float y){
        super(x, y);
        width = MyGdxGame.SCR_WIDTH / 9;
        height = MyGdxGame.SCR_HEIGHT / 9.6f;
        img = new Texture("Terrain2.PNG");


    }

}
