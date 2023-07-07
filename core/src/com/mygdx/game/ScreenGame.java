package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class ScreenGame implements Screen {
    MyGdxGame mgg;
    Texture terrain;
    Texture sky;
    Texture egg;
    Texture rightButtonTexture, leftButtonTexture, upButtonTexture;
    static Texture bullet;
    int gx = 0;
    int gy = 0;
    static Bullet[] playerBullets;
    float gwidth, gheight;

    SolidPlatform solids[] = new SolidPlatform[16];
    Player player;
    ArrayList<Object> objects = new ArrayList<>();

    Button rightButton, leftButton, upButton;
    ScreenGame(MyGdxGame mgg){
        this.mgg = mgg;
        terrain = new Texture("Terrain2.PNG");
        sky = new Texture("Sky.jpg");
        bullet = new Texture("bullet.png");
        egg = new Texture("egg.png");
        rightButtonTexture = new Texture("button_right.png");
        leftButtonTexture = new Texture("button_left.png");
        upButtonTexture = new Texture("button_up.png");

        playerBullets = new Bullet[100];
        player = new Player(egg, SCR_WIDTH / 9,SCR_HEIGHT / 5, 0,500, 5, 14, 0.3f);
        gwidth = SCR_WIDTH / 9;
        gheight = SCR_HEIGHT / 9.6f;

        leftButton = new Button(30, 50, SCR_WIDTH / 4 / (SCR_WIDTH/SCR_HEIGHT), SCR_HEIGHT / 4, leftButtonTexture);
        rightButton = new Button(300, 50, SCR_WIDTH / 4 / (SCR_WIDTH/SCR_HEIGHT), SCR_HEIGHT / 4, rightButtonTexture);
        upButton = new Button(SCR_WIDTH - 290, 50, SCR_WIDTH / 4 / (SCR_WIDTH/SCR_HEIGHT), SCR_HEIGHT / 4, upButtonTexture);

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
        if (Gdx.input.isTouched()) {
            mgg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            if(mgg.touch.x > SCR_WIDTH/2 && mgg.touch.y > SCR_HEIGHT/2 && !Player.onCD){
                player.shoot(playerBullets);
            }
            else if(rightButton.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT)){
                player.update(true, false, false, objects);
            }
            else if(upButton.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT)){
                player.update(false, false, true, objects);
            }
            else if (leftButton.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT)){
                player.update(false, true, false, objects);
            }
            else {
                player.update(false, false, false, objects);
            }
        }

        else {
            player.update(false, false, false, objects);
        }


        player.draw(mgg.batch);
        rightButton.draw(mgg.batch);
        leftButton.draw(mgg.batch);
        upButton.draw(mgg.batch);
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

