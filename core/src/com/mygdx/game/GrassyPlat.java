package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;
import static com.mygdx.game.MyGdxGame.A;
public class GrassyPlat extends SolidPlatform{

    public GrassyPlat(float x, float y){
        super(x, y);
        width = 181.44f * X;
        height = 84.4f * Y;
        img = new Texture("Terrain2.PNG");


    }

}
