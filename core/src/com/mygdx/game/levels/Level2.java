package com.mygdx.game.levels;

import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;

import com.mygdx.game.Dirt;
import com.mygdx.game.EnemyEgg;
import com.mygdx.game.GrassyPlat;
import com.mygdx.game.MyGdxGame;

public class Level2 extends Level {
    float gx = 0;
    float gy = 0;

    float gwidth, gheight;

    public Level2(MyGdxGame mgg) {
        super(mgg);

        gwidth = 181.44f * X;
        gheight = 84.4f * Y;

        //PLATFORMS CREATION START

        for (int i = 0; i < 40; i++) {
            GrassyPlat g = new GrassyPlat(gx, gy);
            solids[i] = g;
            objects.add(g);
            gx += g.width;

        }
        solids[40] = new GrassyPlat(1000 * X, gheight);
        objects.add(solids[40]);

        solids[99] = new Dirt(1000 * X, 0, gwidth, gheight);
        objects.add(solids[99]);

        solids[41] = new Dirt(0, -10000 * Y, gwidth * 40, 10000 * Y);
        objects.add(solids[41]);
        solids[42] = new Dirt(-10000 * X, -10000 * Y, 10000 * X, 10400 * Y);
        objects.add(solids[42]);

        int o = 1;
        for (int i = 43; i < 53; i++) {
            solids[i] = new GrassyPlat(-gwidth * o, 400 * Y);
            objects.add(solids[i]);
            o++;
        }
        o = 0;
        for (int i = 53; i < 63; i++) {
            solids[i] = new GrassyPlat(40 * gwidth + gwidth * o, 500 * Y);
            objects.add(solids[i]);
            o++;
        }
        solids[63] = new Dirt(40 * gwidth, -10000 * Y, 10000 * X, 10500 * Y);
        objects.add(solids[63]);

        solids[64] = new GrassyPlat(2000 * X, gheight * 2);
        objects.add(solids[64]);

        solids[65] = new Dirt(2000 * X, 0, gwidth, gheight * 2);
        objects.add(solids[65]);

        solids[66] = new GrassyPlat(2000 * X + gwidth * 7, gheight * 3);
        objects.add(solids[66]);

        solids[67] = new Dirt(2000 * X + gwidth * 7, 0, gwidth, gheight * 3);
        objects.add(solids[67]);

        solids[68] = new Dirt(2000 * X + gwidth, 0, gwidth * 7, gheight * 3);
        objects.add(solids[68]);
        o = 0;
        for (int i = 69; i <= 74; i++) {
            solids[i] = new GrassyPlat(2000 * X + gwidth + o * gwidth, gheight * 2);
            o++;
        }
        solids[75] = new GrassyPlat(2000 * X + gwidth * 8, gheight * 2);
        objects.add(solids[75]);
        solids[76] = new Dirt(2000 * X + gwidth * 8, 0, gwidth, gheight * 2);
        objects.add(solids[76]);



        //PLATFORMS CREATION END

        //ENEMIES CREATION START

        bob = new EnemyEgg(enemyEggTexture, 100, 1500 * X, gheight, X * 3, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 100, 2003 * X, gheight * 3, X * 3, 0, false, gwidth/3);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 100, 2000 * X + gwidth * 7, gheight * 4, X * 3, 0, false, gwidth / 3);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 4000 * X, gheight, X * 3, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 4000 * X, gheight, X * 4, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 4000 * X, gheight, X * 4.5f, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 4500 * X, gheight, X * 5, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 4000 * X, gheight, X * 5.5f, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 4000 * X, gheight, X * 6, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 4000 * X, gheight, X * 6.5f, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 6000 * X, gheight, X * 7f, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 1, 3500 * X, gheight, X * 7.5f, 0, false, 20000 * X);
        enemyEggs.add(bob);
        objects.add(bob);


        //ENEMIES CREATION END
        eggChild.setX(gwidth * 39);
        eggChild.setY(gheight);
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
        if(mgg.maxLevel < 3) {
            mgg.maxLevel = 3;
            mgg.pref.putInteger("maxLevel", mgg.maxLevel);
            mgg.pref.flush();
        }
    }
}
