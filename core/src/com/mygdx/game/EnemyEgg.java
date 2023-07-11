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
    float gravity;
    float moveSpeed;
    public EnemyEgg(Texture img, float hp, float x, float y, float moveSpeed, float vy, boolean immobile, float maxDistance){
        super(img, hp, x, y, immobile);
        this.maxDistance = maxDistance;

        this.vy = vy;
        gravity = 0.45f;
        width = 390 * X;
        height = 200 * Y;
        setSize(width, height);
        setPosition(x, y);
        this.moveSpeed = moveSpeed;
        this.vx = moveSpeed;
    }

    void update(ArrayList<Object> objects){
        x += vx;
        setPosition(x, y);
        collide(vx, 0, objects);
        y += vy;
        setPosition(x, y);
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
                if (Intersector.overlaps(new Rectangle(s.x, s.y, s.width, s.height), getBoundingRectangle())) {
                    if (vx > 0) {
                        x = s.x - width;
                        this.vx = -moveSpeed;
                        setPosition(x, y);
                    }else {

                        if (vx < 0) {
                            x = s.x + s.width;
                            this.vx = moveSpeed;
                            setPosition(x, y);
                        }
                    }

                    if (vy > 0) {
                        y = s.y - height;
                        setPosition(x, y);
                        this.vy = 0;
                    }

                    if (vy < 0) {
                        y = s.y + s.height;
                        setPosition(x, y);
                        onGround = true;
                        this.vy = 0;
                    }
                }
            }
        }
    }
}
