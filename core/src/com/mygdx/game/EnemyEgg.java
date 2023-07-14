package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.levels.Level;

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
    public int bulletCD; //frames
    int counterCD;
    int direction;
    boolean onCD;
    Sound bitShoot;
    public float v;
    int i;
    public EnemyBullet[] enemyBullets;
    float startingPos;
    boolean justDied;
    public float enemyBulletDmg;
    float vCopy;
    public boolean isBoss;
    public EnemyEgg(Texture img, float hp, float x, float y, float moveSpeed, float vy, boolean immobile, float maxDistance){
        super(img, hp, x, y, immobile);
        this.maxDistance = maxDistance;
        this.vy = vy;
        gravity = 0.45f;
        if (img == Level.bossEggTexture){
            height = X * 300;
            width = Y * 300;
        }
        else {
            width = X * 150;
            height = Y * 150;
        }
        setSize(width, height);
        setPosition(x, y);
        this.moveSpeed = moveSpeed;
        this.vx = moveSpeed;
        flip(true, false);
        passive = true;
        direction = 1;
        bulletCD = 90;
        bitShoot = Gdx.audio.newSound(Gdx.files.internal("shootSound.ogg"));
        i = 0;
        enemyBullets = new EnemyBullet[100];
        startingPos = x;
        justDied = false;
        enemyBulletDmg = 1;
        v = 10 * X;
        isBoss = false;

    }

    public void shoot(EnemyBullet[] enemyBullets) {
        if (!onCD) {
            bitShoot.play();
            v = Math.abs(v);
            onCD = true;
            if (direction == 2){
                v = - v;
            }
            enemyBullets[i] = new EnemyBullet(v, x, y + height / 3.5f, enemyBulletDmg);
            if (v > 0) enemyBullets[i].x += width - 30 * X;
            else{
                enemyBullets[i].x -= SCR_WIDTH / 35.7f;
            }

            if (++i >= 100) {
                i = 0;
            }
        }
    }
    public void update(ArrayList<Object> objects, Player player){
        if(hp <= 0 && !justDied){
            justDied = true;
            vx = MathUtils.random(-5, 5) * X;
            vy = 25 * Y;
            gravity *= 5;
        }
        if (!justDied) {
            if (onCD) {
                counterCD++;
                if (counterCD == bulletCD) {
                    onCD = false;
                    counterCD = 0;
                }
            }
            if (passive) {
                x += vx;
                if (vx > 0 && direction != 1) {
                    flip(true, false);
                    direction = 1;
                }
                if (vx < 0 && direction != 2) {
                    flip(true, false);
                    direction = 2;
                }
            }
            setPosition(x, y);
            collide(vx, 0, objects);
            y += vy;
            setPosition(x, y);
            collide(0, vy, objects);


            passive = true;

            if (Math.abs(x - player.x) < X * 600 && Math.abs(y - player.y) < Y * 210) {
                passive = false;
                if (player.x > x) {
                    if (direction != 1) {
                        flip(true, false);
                        direction = 1;
                    }
                }
                if (player.x < x) {
                    if (direction != 2) {
                        flip(true, false);
                        direction = 2;
                    }

                }
            }
            if (!passive) {
                shoot(enemyBullets);
            }
        }
        else{
            x += vx;
            y += vy;
            setPosition(x, y);
        }
        if(!onGround && vy >= -20 * Y){
            vy -= gravity;
        }
        onGround = false;
    }

    void collide(float vx, float vy, ArrayList<Object> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof SolidPlatform) {
                SolidPlatform s = (SolidPlatform) objects.get(i);
                if (Intersector.overlaps(new Rectangle(s.x, s.y, s.width, s.height), getBoundingRectangle()) && hp > 0) {
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
                if(x > startingPos + maxDistance){

                    this.vx = -moveSpeed;
                    setPosition(x, y);
                }
                if(x < startingPos - maxDistance){

                    this.vx = moveSpeed;
                    setPosition(x, y);
                }
            }
        }
    }
}
