package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;
import static com.mygdx.game.levels.Level.bullAnims;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.animations.BulletImpactAnim;

import java.util.ArrayList;

public class EnemyBullet {
    public float x;
    public float y;
    float vx;
    float width = MyGdxGame.SCR_WIDTH/26.7f;
    float height = MyGdxGame.SCR_HEIGHT/35;
    public Boolean doesExist = true;
    float enemyBulletDmg;
    float animX;

    EnemyBullet(float vx, float x, float y){
        this.x = x;
        this.y = y;
        this.vx = vx;
        enemyBulletDmg = 25;
    }
    public void exist(){

        x += vx;

    }

    public void collide(ArrayList<Object> objects)
    {
        for(int i = 0; i < objects.size(); i++){
            if(objects.get(i) instanceof SolidPlatform){
                SolidPlatform s = (SolidPlatform) objects.get(i);
                if(new Rectangle(s.x, s.y, s.width, s.height).overlaps(new Rectangle(x, y, width, height))) {
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
            if(objects.get(i) instanceof Player){
                Player p = (Player) objects.get(i);
                if(p.getBoundingRectangle().overlaps(new Rectangle(x, y, width, height))) {
                    if (vx > 0) {
                        animX = x + width - X * 70;
                    }else{
                        animX = x - X * 70;
                    }

                    BulletImpactAnim a = new BulletImpactAnim(animX, y - 70 * Y, X * 140, Y * 140);
                    bullAnims.add(a);
                    doesExist = false;
                    p.hp -= enemyBulletDmg;
                }
            }
        }
    }
}

