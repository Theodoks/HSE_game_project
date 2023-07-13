package com.mygdx.game;


import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;
import static com.mygdx.game.levels.Level.bullAnims;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.animations.BulletImpactAnim;

import java.util.ArrayList;

public class Bullet {
    public float x;
    public float y;
    float vx;
    float width = MyGdxGame.SCR_WIDTH / 26.7f;
    float height = MyGdxGame.SCR_HEIGHT / 35;
    public boolean doesExist = true;
    float bulletDMG;
    float animX;

    Bullet(float vx, float x, float y) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        bulletDMG = 34;
    }

    public void exist() {

        x += vx;

    }

    public void collide(ArrayList<Object> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) instanceof SolidPlatform) {
                SolidPlatform s = (SolidPlatform) objects.get(i);
                if (new Rectangle(s.x, s.y, s.width, s.height).overlaps(new Rectangle(x, y, width, height))) {
                    if (vx > 0) {
                        animX = x + width - X * 70;
                    }else{
                        animX = x - X * 70;
                    }

                    BulletImpactAnim a = new BulletImpactAnim(animX, y - 65 * Y, X * 140, Y * 140);
                    bullAnims.add(a);
                    doesExist = false;

                }
            }
            if (objects.get(i) instanceof EnemyEgg) {
                EnemyEgg e = (EnemyEgg) objects.get(i);
                if (e.getBoundingRectangle().overlaps(new Rectangle(x, y, width, height)) && e.hp > 0) {
                    if (vx > 0) {
                        animX = x + width - X * 70;
                    }else{
                        animX = x - X * 70;
                    }

                    BulletImpactAnim a = new BulletImpactAnim(animX, y - 70 * Y, X * 140, Y * 140);
                    bullAnims.add(a);
                    doesExist = false;
                    e.hp -= bulletDMG;
                }
            }
        }
    }
}
