package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MyGdxGame extends ApplicationAdapter {
    static SpriteBatch batch;
    Texture terrain;
    Texture sky;

    static Texture bullet;
    public static Bullet[] playerBullets;
    int gx = 0;
    int gy = 0;
    float gwidth, gheight;
    static float SCR_WIDTH;
    static float SCR_HEIGHT;
    OrthographicCamera camera;
    Vector3 touch;
    SolidPlatform solids[];
    Player player;


    @Override
    public void create() {

        batch = new SpriteBatch();
        terrain = new Texture("Terrain2.PNG");
        sky = new Texture("Sky.jpg");
        bullet = new Texture("bullet.png");
        playerBullets = new Bullet[100]; //Change later
        solids = new SolidPlatform[15];
        touch = new Vector3(0, 0 ,0);
        SCR_WIDTH = Gdx.graphics.getWidth();
        SCR_HEIGHT = Gdx.graphics.getHeight();
        System.out.println(SCR_HEIGHT);
        player = new Player(new Texture("egg.png"), 0, 0 , 600,
                512, SCR_WIDTH / 9,SCR_HEIGHT / 5, 0,0,0,0);
        gwidth = SCR_WIDTH / 9;
        gheight = SCR_HEIGHT / 9.6f;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        for (int i = 0; i < solids.length; i++) {
            SolidPlatform g = new SolidPlatform(terrain, gx, gy, gwidth, gheight);
            solids[i] = g;
            gx += gwidth;

        }
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(sky,0,0, SCR_WIDTH,SCR_HEIGHT);
        for (int j = 0; j < solids.length; j++) {
            solids[j].exist();
        }
        for (int i = 0; i < playerBullets.length; i++) {
            if (playerBullets[i] != null) {
                playerBullets[i].exist();
            }
        }
        player.draw(batch);

        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(touch.x > SCR_WIDTH / 2 && touch.y < SCR_HEIGHT / 2){
                Player.shoot();
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        terrain.dispose();

    }

}
