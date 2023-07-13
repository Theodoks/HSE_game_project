package com.mygdx.game.levels;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;
import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;

import com.mygdx.game.Dirt;
import com.mygdx.game.EggChild;
import com.mygdx.game.GrassyPlat;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.levels.Level;

public class Level0 extends Level {
    float gx = 0;
    float gy = 0;

    float gwidth, gheight;

    public Level0(MyGdxGame mgg) {
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
        eggChild.setY(gheight);
        eggChild.setX(3380 * X);
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
}
