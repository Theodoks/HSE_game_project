package com.mygdx.game;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;
import static com.mygdx.game.MyGdxGame.A;

public class Bullet {
    float x = Player.x;
    float y = Player.y + (MyGdxGame.SCR_HEIGHT/14.35f);
    float vx = 21 * X;
    float vy = 0;
    float width = MyGdxGame.SCR_WIDTH/26.7f;
    float height = MyGdxGame.SCR_HEIGHT/35;
    Boolean doesExist = true;

    Bullet(float vx){
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
