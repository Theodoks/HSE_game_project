package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Sprite {
    static float x;
    static float y;
    float vx, vy;
    static int i = 0;
    float width, height;
    boolean onGround, bodyRotation = true;
    float moveSpeed, powerJump, gravity;

    Player(Texture img, float width, float height, float x, float y, float moveSpeed, float powerJump, float gravity) {
        super(img, 0, 0, img.getWidth(), img.getHeight());
        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
        this.powerJump = powerJump;
        this.width = width;
        this.height = height;
        this.vx = 0;
        this.vy = 0;
        this.gravity = gravity;
        setSize(width, height);
        setPosition(x, y);
    }
    public static void shoot(){
        MyGdxGame.playerBullets[i] = new Bullet();
        if(++i >= 100){
            i = 0;
        }
    }
    void update(boolean right, boolean left, boolean up, ArrayList<Object> objects){
        x += vx;
        setPosition(x, y);
        collide(vx, 0, objects);
        y += vy;
        setPosition(x, y);
        collide(0, vy, objects);
        if(up){
            if(onGround){
                vy = powerJump;
            }
        }
        if(left){
            vx = -moveSpeed;
            if(bodyRotation){
                flip(true, false);
                bodyRotation = false;
            }
        }
        if(right){
            vx = moveSpeed;
            if (!bodyRotation){
                flip(true, false);
                bodyRotation = true;
            }
        }
        if(!right && !left){
            vx = 0;
        }
        if(!onGround){
            vy -= gravity;
        }
        onGround = false;
    }
        void collide(float vx, float vy, ArrayList<Object> objects)
        {
            for(int i = 0; i < objects.size(); i++){
                if(objects.get(i) instanceof SolidPlatform){
                    SolidPlatform s = (SolidPlatform) objects.get(i);
                    if(Intersector.overlaps(new Rectangle(s.x, s.y, s.width, s.height), getBoundingRectangle())){
                        if(vx > 0){
                            x = s.x - width;
                            setPosition(x, y);
                        }

                        if(vx < 0){
                            x = s.x + s.width;
                            setPosition(x, y);
                        }
                        if(vy > 0){
                            y = s.y - height;
                            setPosition(x, y);
                            this.vy = 0;
                        }
                        if(vy < 0){
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
