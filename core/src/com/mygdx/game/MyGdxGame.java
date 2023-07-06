package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MyGdxGame extends ApplicationAdapter {
    static SpriteBatch batch;
    Texture terrain;
    Texture sky;
    int gx = 0;
    int gy = 0;
    float gwidth, gheight;
    float SCR_WIDTH;
    float SCR_HEIGHT;
    OrthographicCamera camera;

    SolidPlatform solids[] = new SolidPlatform[15];
    Player player;

    @Override
    public void create() {

        batch = new SpriteBatch();
        terrain = new Texture("Terrain.PNG");
        sky = new Texture("Sky.jpg");
        SCR_WIDTH = Gdx.graphics.getWidth();
        SCR_HEIGHT = Gdx.graphics.getHeight();
        player = new Player(new Texture("egg.png"), SCR_WIDTH / 9,SCR_HEIGHT / 5, 0,0, 1, 1, 1);
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
        player.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        terrain.dispose();

    }

}
