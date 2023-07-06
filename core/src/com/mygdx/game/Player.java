package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Sprite {
    static float x, y;
    float vx, vy;
    static int i = 0;
    boolean onGround, bodyRotation = true;
    float moveSpeed, powerJump, gravity;

    Player(Texture img, float width, float height, float x, float y, float moveSpeed, float powerJump, float gravity) {
        super(img, 0, 0, img.getWidth(), img.getHeight());
        this.x = x;
        this.y = y;
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
        y += vy;
        setPosition(x, y);
        collide(vx, vy, objects);
        if(up){
            if(onGround){
                vy = -powerJump;
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
            vy += gravity;
        }
        onGround = false;
    }
        void collide(float vx, float vy, ArrayList<Object> objects)
        {
            for(int i = 0; i < objects.size(); i++){

            }
        }
}
