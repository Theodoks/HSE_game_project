package com.mygdx.game;


import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Bullet {
    public float x;
    public float y;
    float vx;
    float width = MyGdxGame.SCR_WIDTH / 26.7f;
    float height = MyGdxGame.SCR_HEIGHT / 35;
    public boolean doesExist = true;
    float bulletDMG;

    Bullet(float vx, float x, float y) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        bulletDMG = 25;
    }

    public void exist() {

        x += vx;

    }

    public void collide(ArrayList<Object> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof SolidPlatform) {
                SolidPlatform s = (SolidPlatform) objects.get(i);
                if (new Rectangle(s.x, s.y, s.width, s.height).overlaps(new Rectangle(x, y, width, height))) {
                    doesExist = false;

                }
            }
            if (objects.get(i) instanceof EnemyEgg) {
                EnemyEgg e = (EnemyEgg) objects.get(i);
                if (e.getBoundingRectangle().overlaps(new Rectangle(x, y, width, height))) {
                    doesExist = false;
                    e.hp -= bulletDMG;
                }
            }
        }
    }
}
