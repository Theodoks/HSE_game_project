package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;
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
    boolean passive;
    int bulletCD; //frames
    int counterCD;
    int direction;
    boolean onCD;
    Sound bitShoot;
    float v;
    int i;

    public EnemyEgg(Texture img, float hp, float x, float y, float moveSpeed, float vy, boolean immobile, float maxDistance){
        super(img, hp, x, y, immobile);
        this.maxDistance = maxDistance;
        this.vy = vy;
        gravity = 0.45f;
        width = X * 190;
        height = Y * 190;
        setSize(width, height);
        setPosition(x, y);
        this.moveSpeed = moveSpeed;
        this.vx = moveSpeed;
        flip(true, false);
        passive = true;
        direction = 1;
        bulletCD = 60;
        bitShoot = Gdx.audio.newSound(Gdx.files.internal("shootSound.ogg"));
        i = 0;
    }

    public void shoot(EnemyBullet[] enemyBullets) {
        if (!onCD) {
            bitShoot.play();
            onCD = true;
            if (direction == 1) v = 21 * X;
            else v = -21 * X;
            enemyBullets[i] = new EnemyBullet(v, x, y + (MyGdxGame.SCR_HEIGHT/14.35f));
            if (v == 21 * X) enemyBullets[i].x += 100 * X;

            if (++i >= 100) {
                i = 0;
            }
        }
    }
    void update(ArrayList<Object> objects, Player player){
        if(onCD){
            counterCD++;
            if (counterCD == bulletCD) {
                onCD = false;
                counterCD = 0;
            }
        }
        if(passive) {
            x += vx;
        }
        setPosition(x, y);
        collide(vx, 0, objects);
        y += vy;
        setPosition(x, y);
        collide(0, vy, objects);

        passive = true;

        if(Math.abs(x - player.x) < X * 800 && Math.abs(y - player.y) < Y * 210){
            passive = false;
            if(player.x > x){
                if(direction != 1) {flip(true, false); direction = 1;}
            }
            if(player.x < x){
                if(direction != 2) {flip(true, false); direction = 2;};
            }
        }


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
                        flip(true, false);
                        direction = 2;
                        this.vx = -moveSpeed;
                        setPosition(x, y);
                    }else {

                        if (vx < 0) {
                            x = s.x + s.width;
                            flip(true, false);
                            direction = 1;
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
