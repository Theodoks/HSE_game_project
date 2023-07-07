package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class ScreenGame implements Screen {
    MyGdxGame mgg;
    Texture terrain;
    Texture sky;
    static Texture bullet;
    int gx = 0;
    int gy = 0;
    static Bullet[] playerBullets;
    float gwidth, gheight;

    SolidPlatform solids[] = new SolidPlatform[16];
    Player player;
    ArrayList<Object> objects = new ArrayList<>();
    ScreenGame(MyGdxGame mgg){
        this.mgg = mgg;
        terrain = new Texture("Terrain2.PNG");
        sky = new Texture("Sky.jpg");
        bullet = new Texture("bullet.png");
        playerBullets = new Bullet[10];
        player = new Player(new Texture("egg.png"), SCR_WIDTH / 9,SCR_HEIGHT / 5, 0,500, 20, 20, 1);
        gwidth = SCR_WIDTH / 9;
        gheight = SCR_HEIGHT / 9.6f;
        for (int i = 0; i < solids.length; i++) {
            SolidPlatform g = new SolidPlatform(terrain, gx, gy, gwidth, gheight);
            solids[i] = g;
            objects.add(g);
            gx += gwidth;

        }
        solids[15] = new SolidPlatform(terrain, 1000, 120, gwidth, gheight);
        objects.add(solids[15]);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mgg.batch.begin();
        mgg.batch.draw(sky,0,0, SCR_WIDTH,SCR_HEIGHT);
        for (int j = 0; j < solids.length; j++) {
            if(solids [j] != null){
                solids[j].exist();
            }


        }

        for (int i = 0; i < playerBullets.length; i++) {
            if (playerBullets[i] != null && playerBullets[i].doesExist){
                playerBullets[i].exist();
                mgg.batch.draw(bullet, playerBullets[i].x, playerBullets[i].y, SCR_HEIGHT /15, SCR_HEIGHT / 25);
                playerBullets[i].collide(playerBullets[i].vx, playerBullets[i].vy, objects);
            }

        }
        if (Gdx.input.isTouched()) {
            mgg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            if(mgg.touch.x > SCR_WIDTH/2 && mgg.touch.y > SCR_HEIGHT/2){
                Player.shoot(playerBullets);
            }
            if(mgg.touch.x < SCR_WIDTH/2 && mgg.touch.y > SCR_HEIGHT/2){
                player.update(true, false, false, objects);
            }
            if(mgg.touch.x < SCR_WIDTH/2 && mgg.touch.y < SCR_HEIGHT/2){
                player.update(false, false, true, objects);
            }

        }

        else {
            player.update(false, false, false, objects);
        }


        player.draw(mgg.batch);
        mgg.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mgg.batch.dispose();
        terrain.dispose();
    }
}
