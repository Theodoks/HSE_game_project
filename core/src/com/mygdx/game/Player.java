package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Sprite {
    float x, y;
    float vx, vy;
    float width, height;
    boolean onGround, bodyRotation = true;
    float moveSpeed, powerJump, gravity;

    Player(Texture img, float width, float height, float x, float y, float moveSpeed, float powerJump, float gravity) {
        super(img, 0, 0, img.getWidth(), img.getHeight());
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vx = 0;
        this.vy = 0;
        this.gravity = gravity;
        setSize(width, height);
        setPosition(x, y);
    }
    void update(boolean right, boolean left, boolean up, ArrayList<Object> objects){
        x += vx;
        y += vy;
        setPosition(x, y);
        collide(objects);
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
        void collide(ArrayList<Object> objects)
        {
            for(int i = 0; i < objects.size(); i++){
                if(objects.get(0) instanceof SolidPlatform){
                    SolidPlatform s = (SolidPlatform) objects.get(0);
                    if((s.x < x + width && x + width < s.x + s.width) || (s.y < y + height && y + height< s.y + s.height) || (s.x < x && x < s.x + s.width) || (s.y < y && y < s.y + s.height)){
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
                            vy = 0;
                        }
                        if(vy < 0){
                            y = s.y + s.height;
                            setPosition(x, y);
                            onGround = true;
                            vy = 0;
                        }
                    }
                }


            }
        }
}
