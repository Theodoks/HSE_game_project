package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class EnemyBullet {
    float x;
    float y;
    float vx;
    float width = MyGdxGame.SCR_WIDTH/26.7f;
    float height = MyGdxGame.SCR_HEIGHT/35;
    Boolean doesExist = true;

    EnemyBullet(float vx, float x, float y){
        this.x = x;
        this.y = y;
        this.vx = vx;
    }
    public void exist(){

        x += vx;

    }

    void collide(ArrayList<Object> objects)
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

