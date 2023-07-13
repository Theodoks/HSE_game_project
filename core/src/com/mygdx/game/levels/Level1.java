package com.mygdx.game.levels;

import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;

import com.mygdx.game.Dirt;
import com.mygdx.game.GrassyPlat;
import com.mygdx.game.MyGdxGame;

public class Level1 extends Level {
    float gx = 0;
    float gy = 0;

    float gwidth, gheight;

    public Level1(MyGdxGame mgg) {
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
        solids[54] = new GrassyPlat(  solids[9].x, 400 * Y);
        objects.add(solids[54]);
        solids[55] = new GrassyPlat(  solids[9].x + 600 * X, 200 * Y);
        objects.add(solids[55]);
        solids[56] = new GrassyPlat(  solids[9].x - 830 * X, 400 * Y);
        objects.add(solids[56]);
        solids[57] = new GrassyPlat(  solids[9].x - 1380 * X, 500 * Y);
        objects.add(solids[57]);
        solids[58] = new GrassyPlat(  solids[0].x + 10*X, 730* Y);
        solids[62] = new GrassyPlat(  solids[0].x, 730* Y);
        objects.add(solids[62]);
        solids[63] = new GrassyPlat(  solids[0].x, 970* Y);
        objects.add(solids[63]);
        solids[59] = new GrassyPlat(  solids[0].x + 5*X, 970* Y);
        objects.add(solids[58]);
        objects.add(solids[59]);
        solids[60] = new GrassyPlat(  solids[0].x, 1200* Y);
        objects.add(solids[60]);
        solids[61] = new GrassyPlat(  solids[0].x, 1440* Y);
        objects.add(solids[61]);
        solids[64] = new GrassyPlat(  solids[0].x + 830 * X, 1440* Y);
        objects.add(solids[64]);
        eggChild.setY(1450* Y + gheight);
        eggChild.setX(solids[0].x + 830 * X);
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
        solids[32] = new Dirt(-10000 * X, -10000 * Y, 10000 * X, 15000 * Y);
        objects.add(solids[32]);
        int o = 1;
        for (int i = 33; i < 43; i++) {
            solids[i] = new GrassyPlat(-gwidth * o, 5000 * Y);
            objects.add(solids[i]);
            o++;
        }
        solids[43] = new Dirt(solids[19].x, -10000 * Y, 10000 * X, 15000 * Y);
        objects.add(solids[43]);
        o = 0;
        for (int i = 44; i < 54; i++) {
            solids[i] = new GrassyPlat(  solids[19].x + gwidth * o, 5000 * Y);
            objects.add(solids[i]);
            o++;
        }
        //PLATFORMS CREATION END
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
        mgg.maxLevel = 2;
    }
}
