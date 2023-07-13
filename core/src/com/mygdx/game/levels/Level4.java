package com.mygdx.game.levels;

import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;

import com.mygdx.game.Dirt;
import com.mygdx.game.EnemyEgg;
import com.mygdx.game.GrassyPlat;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;


public class Level4 extends Level {
    float gx = 0;

    float gy = 0;

    float gwidth, gheight;

    public Level4(MyGdxGame mgg) {
        super(mgg);
        gwidth = 181.44f * X;
        gheight = 84.4f * Y;

        Player.hp = 9;

        //PLATFORMS CREATION START

        for (int i = 0; i < 20; i++) {
            GrassyPlat g = new GrassyPlat(gx, gy);
            solids[i] = g;
            objects.add(g);
            gx += g.width;

        }

        /*
        for (int i = 21; i < 31; i++) {
            GrassyPlat g = new GrassyPlat(gx, gy);
            solids[i] = g;
            objects.add(g);
            gx += 100 * X;
            gy += SCR_HEIGHT / 10;
        }*/
        solids[31] = new Dirt(0, -10000 * Y, gwidth * 20, 10000 * Y);
        objects.add(solids[31]);
        solids[32] = new Dirt(-10000 * X, -10000 * Y, 10000 * X, 10400 * Y);
        objects.add(solids[32]);
        int o = 1;
        for (int i = 33; i < 43; i++) {
            solids[i] = new GrassyPlat(-gwidth * o, 400 * Y);
            objects.add(solids[i]);
            o++;
        }
        solids[43] = new Dirt(solids[19].x, -10000 * Y, 10000 * X, 10400 * Y);
        objects.add(solids[43]);
        o = 0;
        for (int i = 44; i < 54; i++) {
            solids[i] = new GrassyPlat(solids[19].x + gwidth * o, 400 * Y);
            objects.add(solids[i]);
            o++;
        }
        o = 0;
        for (int i = 54; i < 56; i++) {
            solids[i] = new GrassyPlat(450 * X + o * 700 * X, 235 * Y);
            objects.add(solids[i]);
            o++;
        }



        for (int i = 0; i < 15; i++) {
            bob = new EnemyEgg(enemyEggTexture, 100, 0, Y * i * 5000, X * i, 10 * Y, false, gwidth * X * 20);
            enemyEggs.add(bob);
            objects.add(bob);

        }
        bob = new EnemyEgg(enemyEggTexture, 300, 2600 * X, 500 * Y, 2 * X, 0, false, gwidth * X * 20);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 300, 2400 * X, 500 * Y, 2 * X, 0, false, gwidth * X * 20);
        enemyEggs.add(bob);
        objects.add(bob);

        bob = new EnemyEgg(bossEggTexture, 1000, 2500 * X + gwidth, 500 * Y, X * 2, 0, false, 200 * X);
        bob.enemyBulletDmg = 3;
        bob.v = 4 * X;
        bob.isBoss = true;
        bob.bulletCD = 300;
        enemyEggs.add(bob);
        objects.add(bob);

        objects.add(eggChild);
    }
    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void win() {
        if(mgg.maxLevel < 5) {
            mgg.maxLevel = 5;
            mgg.pref.putInteger("maxLevel", mgg.maxLevel);
            mgg.pref.flush();
        }
    }
}
