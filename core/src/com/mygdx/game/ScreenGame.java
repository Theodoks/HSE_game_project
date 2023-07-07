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
    boolean right;
    boolean left;
    boolean up;
    boolean shoot;
    ScreenGame(MyGdxGame mgg){
        this.mgg = mgg;
        terrain = new Texture("Terrain2.PNG");
        sky = new Texture("Sky.jpg");
        bullet = new Texture("bullet2.png");
        playerBullets = new Bullet[100];
        player = new Player(new Texture("egg.png"), SCR_WIDTH / 9,SCR_HEIGHT / 5, 0,500, SCR_WIDTH / 190, SCR_HEIGHT / 60, SCR_HEIGHT / 1800);
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
                mgg.batch.draw(bullet, playerBullets[i].x, playerBullets[i].y, SCR_WIDTH / 26.7f, SCR_HEIGHT / 35);
                playerBullets[i].collide(playerBullets[i].vx, playerBullets[i].vy, objects);
            }

        }
        right = false;
        left = false;
        up = false;
        shoot = false;
        for (int i = 0; i < 2; i++) {


            if (Gdx.input.isTouched(i)) {
                mgg.touch.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);


                if (mgg.touch.x < SCR_WIDTH / 2 && mgg.touch.y > SCR_HEIGHT / 2) {
                    right = true;

                }  if (mgg.touch.x < SCR_WIDTH / 2 && mgg.touch.y < SCR_HEIGHT / 2) {
                    up = true;
                }
                if (mgg.touch.x > SCR_WIDTH / 2 && mgg.touch.y > SCR_HEIGHT / 2 && !Player.onCD) {
                    shoot = true;
                }

            }
        }
        player.update(right, left, up, objects);
        if (shoot) player.shoot(playerBullets);

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

