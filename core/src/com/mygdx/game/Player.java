package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;
public class Player extends Sprite {
    public float x;
    public  float y;
    float vx, vy;
    static int i = 0;
    public float width, height;
    public boolean onGround, bodyRotation = true;
    float moveSpeed, powerJump, gravity;
    float bulletCD;
    float counterCD;
    public boolean onCD;
    int direction;
    float v;
    public boolean isWinner = false;
    static Sound bitShoot;
    static public int hp;
    public boolean dead;
    public Player(Texture img, int hp, float width, float height, float x, float y, float moveSpeed, float powerJump, float gravity) {
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
        this.hp = hp;
        bulletCD = 20; // frames
        counterCD = 0;
        onCD = false;
        setSize(width, height);
        setPosition(x, y);
        direction = 1; // 1 = right, 2 = left
        bitShoot = Gdx.audio.newSound(Gdx.files.internal("shootSound.ogg"));
        dead = false;
    }

    public void shoot(Bullet[] playerBullets) {
        if (!onCD) {
            bitShoot.play();
            onCD = true;
            if (direction == 1) v = 13 * X;
            else v = -13 * X;
            playerBullets[i] = new Bullet(v, x, y + (MyGdxGame.SCR_HEIGHT/14.35f));
            if (v == 13 * X) playerBullets[i].x += 100 * X;

            if (++i >= 100) {
                i = 0;
            }
        }
    }

    public void update(boolean right, boolean left, boolean up, ArrayList<Object> objects) {
        if(hp <= 0){
            dead = true;
        }
        x += vx;
        setPosition(x, y);
        collide(vx, 0, objects);
        y += vy;
        setPosition(x, y);
        collide(0, vy, objects);
        if (onCD) {
            counterCD++;
            if (counterCD == bulletCD) {
                onCD = false;
                counterCD = 0;
            }
        }

        if (up) {
            if (onGround) {
                vy = powerJump;
            }
        }

        if (left) {
            vx = -moveSpeed;
            if (bodyRotation) {
                flip(true, false);
                bodyRotation = false;
                direction = 2;

            }
        }

        if (right) {
            vx = moveSpeed;
            if (!bodyRotation) {
                flip(true, false);
                bodyRotation = true;
                direction = 1;
            }
        }

        if (!right && !left) {
            vx = 0;
        }

        if (!onGround) {
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
                        setPosition(x, y);
                    }

                    if (vx < 0) {
                        x = s.x + s.width;
                        setPosition(x, y);
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
            if (objects.get(i) instanceof EggChild) {
                EggChild eggChild = (EggChild) objects.get(i);
                if (Intersector.overlaps(eggChild.getBoundingRectangle(), getBoundingRectangle())) {
                    isWinner = true;
                }
            }

        }
    }
}
