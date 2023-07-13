package com.mygdx.game.levels;

import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;

import com.mygdx.game.Dirt;
import com.mygdx.game.EnemyEgg;
import com.mygdx.game.GrassyPlat;
import com.mygdx.game.MyGdxGame;

public class Level3 extends Level{
    float gx = 0;
    float gy = 0;

    float gwidth, gheight;
    public Level3(MyGdxGame mgg) {
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
        eggChild.setY(572 + gheight);
        eggChild.setX(3000 * X + gwidth);
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
            solids[i] = new GrassyPlat(  solids[19].x + gwidth * o, 400 * Y);
            objects.add(solids[i]);
            o++;
        }

        solids[54] = new GrassyPlat(1000 * X, 232 * Y); //enemy on it
        objects.add(solids[54]);

        solids [55] = new GrassyPlat(1500 * X, 464 * Y);
        objects.add(solids[55]);

        solids [56] = new GrassyPlat(1000 * X, 696 * Y);
        objects.add(solids[56]);

        solids [57] = new GrassyPlat(1000 * X - gwidth, 696 * Y);
        objects.add(solids[57]);

        solids[58] = new GrassyPlat(1500 * X, 928 * Y);
        objects.add(solids[58]);

        solids[59] = new GrassyPlat(2500 * X, 350 * Y);
        objects.add(solids[59]);

        solids[60] = new GrassyPlat(2500 * X + gwidth, 350 * Y);
        objects.add(solids[60]);

        solids[61] = new GrassyPlat(3000 * X + gwidth, 572 * Y);

        //PLATFORMS CREATION END

        //ENEMY CREATION START
        bob = new EnemyEgg(enemyEggTexture, 100, 1000 * X, Y * 235 + gheight, X * 3, 0, false, gwidth / 3);
        enemyEggs.add(bob);
        objects.add(bob);

        bob = new EnemyEgg(enemyEggTexture, 100, 1000 * X, Y * 696 + gheight, X * 3, 0, false, gwidth / 3);
        enemyEggs.add(bob);
        objects.add(bob);

        bob = new EnemyEgg(enemyEggTexture, 100, 1000 * X - gwidth, Y * 696 + gheight, X * 3, 0, false, gwidth / 3);
        enemyEggs.add(bob);
        objects.add(bob);

        bob = new EnemyEgg(enemyEggTexture, 100, 1500 * X, Y * 467 + gheight, X * 3, 0, false, gwidth / 3);
        enemyEggs.add(bob);
        objects.add(bob);

        bob = new EnemyEgg(enemyEggTexture, 100, 2500 * X + gwidth - bob.getWidth() / 2, Y * 350 + gheight, X * 3, 0, false, gwidth * 0.8f);
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

}

