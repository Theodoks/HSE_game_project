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

        for (int i = 0; i < 20; i++) {
            GrassyPlat g = new GrassyPlat(gx, gy);
            solids[i] = g;
            objects.add(g);
            gx += g.width;

        }
        solids[20] = new GrassyPlat(1000 * X, Y * 120);
        objects.add(solids[20]);
        gx = 500 * X;
        gy = 202.5f * Y;

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
        //PLATFORMS CREATION END

        //ENEMIES CREATION START

        bob = new EnemyEgg(enemyEggTexture, 100, 100 * X, Y * 1000, X * 3, 0, false, 200 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 100, 500 * X, Y * 1000, X * 3, 0, false, 200 * X);
        enemyEggs.add(bob);
        objects.add(bob);
        bob = new EnemyEgg(enemyEggTexture, 100, 1000 * X, Y * 1000, X * 3, 0, false, 600 * X);
        enemyEggs.add(bob);
        objects.add(bob);

        //ENEMIES CREATION END

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
        }
    }
}
