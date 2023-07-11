package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;
import static com.mygdx.game.MyGdxGame.A;

import java.util.ArrayList;

public class EnemyEgg extends Enemy{
    float maxDistance;
    float height;
    float width;
    float vx, vy;
    boolean onGround = false;
    float gravity = 0.45f;
    public EnemyEgg(float hp, float movespeed, float x, float y, boolean immobile, float maxDistance){
        super(hp, movespeed, x, y, immobile);
        this.maxDistance = maxDistance;
        vx = movespeed;
        vy = 0;
        img = new Texture("egg_enemy.png");
        width = 390 * X;
        height = 200 * Y;
    }

    void update(ArrayList<Object> objects){
        x += vx;
        collide(vx, 0, objects);
        y += vy;
        collide(0, vy, objects);
        if(!onGround){
            vy -= gravity;
        }
        onGround = false;
    }

    void collide(float vx, float vy, ArrayList<Object> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof SolidPlatform) {
                SolidPlatform s = (SolidPlatform) objects.get(i);
                if (Intersector.overlaps(new Rectangle(s.x, s.y, s.width, s.height), new Rectangle(x, y, width, height))) {
                    if (vx > 0) {

                        vx = -vx;
                    }

                    if (vx < 0) {

                        vx = -vx;
                    }
                    /*if (vy > 0) {
                        y = s.y - height;

                        this.vy = 0;
                    }*/
                    if (vy < 0) {
                        //y = s.y + s.height;

                        onGround = true;
                        this.vy = 0;
                    }
                }
            }
        }
    }
}
