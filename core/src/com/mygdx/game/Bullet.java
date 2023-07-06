package com.mygdx.game;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Bullet {
    float x = Player.x + 100;
    float y = Player.y + (MyGdxGame.SCR_HEIGHT/14.85f);
    float vx = 14;
    float vy = 0;
    float width = MyGdxGame.SCR_WIDTH/15;
    float height = MyGdxGame.SCR_HEIGHT/25;
    Boolean doesExist = true;
    public void exist(){

        MyGdxGame.batch.draw(MyGdxGame.bullet, x, y , width, height);
        x += vx;

    }

    void collide(float vx, float vy, ArrayList<Object> objects)
    {
        for(int i = 0; i < objects.size(); i++){
            if(objects.get(i) instanceof SolidPlatform){
                SolidPlatform s = (SolidPlatform) objects.get(i);

                if(new Rectangle(s.x, s.y, s.width, s.height).overlaps(new Rectangle(x, y, width, height))) {
                    doesExist = false;

                }
            }
        }
    }
}
