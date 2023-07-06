package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Objects;

public class MyGdxGame extends ApplicationAdapter {
    static SpriteBatch batch;
    Texture terrain;
    Texture sky;
    static Texture bullet;
    int gx = 0;
    int gy = 0;
    static Bullet[] playerBullets;
    Vector3 touch;
    float gwidth, gheight;
    static float SCR_WIDTH;
    static float SCR_HEIGHT;
    OrthographicCamera camera;

    SolidPlatform solids[] = new SolidPlatform[16];
    Player player;
    ArrayList<Object> objects = new ArrayList<>();

    @Override
    public void create() {

        batch = new SpriteBatch();
        terrain = new Texture("Terrain2.PNG");
        sky = new Texture("Sky.jpg");
        bullet = new Texture("bullet.png");
        touch = new Vector3(0, 0 ,0);
        playerBullets = new Bullet[100];
        SCR_WIDTH = Gdx.graphics.getWidth();
        SCR_HEIGHT = Gdx.graphics.getHeight();
        player = new Player(new Texture("egg.png"), SCR_WIDTH / 9,SCR_HEIGHT / 5, 0,500, 1, 1, 1);
        gwidth = SCR_WIDTH / 9;
        gheight = SCR_HEIGHT / 9.6f;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        for (int i = 0; i < solids.length; i++) {
            SolidPlatform g = new SolidPlatform(terrain, gx, gy, gwidth, gheight);
            solids[i] = g;
            objects.add(g);
            gx += gwidth;

        }
        solids[15] = new SolidPlatform(terrain, 1000, 80, gwidth, gheight);
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(sky,0,0, SCR_WIDTH,SCR_HEIGHT);
        for (int j = 0; j < solids.length; j++) {
            if(solids [j] != null){
                solids[j].exist();
            }

        }
        for (int i = 0; i < playerBullets.length; i++) {
            if (playerBullets[i] != null){
                playerBullets[i].exist();
            }
        }
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(touch.x > SCR_WIDTH/2 && touch.y < SCR_HEIGHT/2){
                Player.shoot();
            }

        }
        player.update(false, false, false, objects);
        player.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        terrain.dispose();

    }

}
