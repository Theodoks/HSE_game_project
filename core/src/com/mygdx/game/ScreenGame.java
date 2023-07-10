package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Vector;

public class ScreenGame implements Screen {
    MyGdxGame mgg;
    Texture terrain;
    Texture sky;
    Texture rightButtonTexture, leftButtonTexture, upButtonTexture, shootButtonTexture;
    static Texture bullet;
    int gx = 0;
    int gy = 0;
    static Bullet[] playerBullets;
    float gwidth, gheight;



    SolidPlatform solids[] = new SolidPlatform[22];
    Player player;
    Gun gun;
    ArrayList<Object> objects = new ArrayList<>();
    boolean right;
    boolean left;
    boolean up;
    boolean shoot;
    int fps;
    float lerp;
    Button rightButton, leftButton, upButton, shootButton;
    Vector3 position;
    Background skyBG;

    ScreenGame(MyGdxGame mgg) {
        this.mgg = mgg;
        terrain = new Texture("Terrain2.PNG");
        sky = new Texture("Sky2.png");
        bullet = new Texture("bullet2.png");
        rightButtonTexture = new Texture("button_right.png");
        leftButtonTexture = new Texture("button_left.png");
        upButtonTexture = new Texture("button_up.png");
        shootButtonTexture = new Texture(("button_shoot.png"));

        fps = 60;

        playerBullets = new Bullet[100];
        player = new Player(new Texture("justEgg.png"), SCR_WIDTH / 12.5f, SCR_HEIGHT / 5, 0, 500, SCR_WIDTH / 190, SCR_HEIGHT / 60, SCR_HEIGHT / 1800);
        gun = new Gun(new Texture("egg_gun.png"), player.getX(), player.getY(), SCR_WIDTH / 9.5f,SCR_HEIGHT / 15);
        gwidth = SCR_WIDTH / 9;
        gheight = SCR_HEIGHT / 9.6f;

        leftButton = new Button(30, 50, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, leftButtonTexture);
        rightButton = new Button(300, 50, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, rightButtonTexture);
        upButton = new Button(SCR_WIDTH - 290, 50, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, upButtonTexture);
        shootButton = new Button(SCR_WIDTH - 560, 50, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, shootButtonTexture);

        for (int i = 0; i < solids.length; i++) {
            SolidPlatform g = new SolidPlatform(terrain, gx, gy, gwidth, gheight);
            solids[i] = g;
            objects.add(g);
            gx += gwidth;

        }
        solids[21] = new SolidPlatform(terrain, 1000, 120, gwidth, gheight);
        objects.add(solids[21]);
        mgg.camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        lerp = 0.12f;
        position = mgg.camera.position;
        skyBG = new Background(sky, mgg);
    }

    private long diff, start = System.currentTimeMillis();

    public void limitFPS(int fps) {
        if (fps > 0) {
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000 / fps;
            if (diff < targetDelay) {
                try {
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {
                }
            }
            start = System.currentTimeMillis();
        }
    }

    /*public void updateCam(float delta, float Xtarget, float Ytarget){
        Vector3 target = new Vector3(Xtarget, Ytarget, 0);
        final float speed = delta, ispeed = 1.0f - speed;
        Vector3 cameraPosition = mgg.camera.position;
        cameraPosition.scl(ispeed);
        target.scl(speed);
        cameraPosition.add(target);
        mgg.camera.position.set(cameraPosition);
    }*/

    @Override
    public void render(float delta) {
        limitFPS(fps);
        mgg.batch.begin();

        Gdx.gl.glClearColor(0, 0 ,0 ,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //mgg.batch.draw(sky, mgg.camera.position.x - SCR_WIDTH/2, mgg.camera.position.y -SCR_HEIGHT/2, SCR_WIDTH, SCR_HEIGHT);
        skyBG.exist();



        for (int j = 0; j < solids.length; j++) {
            if (solids[j] != null) {
                solids[j].exist();
            }


        }



        for (int i = 0; i < playerBullets.length; i++) {
            if (playerBullets[i] != null && playerBullets[i].doesExist) {
                playerBullets[i].exist();
                mgg.batch.draw(bullet, playerBullets[i].x, playerBullets[i].y, SCR_WIDTH / 26.7f, SCR_HEIGHT / 35);
                playerBullets[i].collide(playerBullets[i].vx, playerBullets[i].vy, objects);
            }

        }
        right = false;
        left = false;
        up = false;
        shoot = false;
        for (int i = 0; i < 3; i++) {


            if (Gdx.input.isTouched(i)) {
                mgg.touch.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);


                if (shootButton.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT) && !Player.onCD) {
                    shoot = true;
                }
                if (rightButton.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT)) {
                    right = true;
                }
                if (upButton.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT)) {
                    up = true;
                }
                if (leftButton.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT)) {
                    left = true;
                }
            }




        }
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        //mgg.camera.position.set(player.x + player.width/2, player.y + player.height/2, 0);
        position.x += (player.x + player.width/2 - position.x) * lerp;
        position.y += (player.y + player.height/2 - position.y) * lerp;
        if (shoot) player.shoot(playerBullets);
        player.update(right, left, up, objects);
        mgg.camera.update();
        if(gun.bodyRotation) {
            gun.update(player.x, player.y + player.height / 5f, player.bodyRotation);
        }
        else {
            gun.update(player.x + player.getWidth() - gun.getWidth(), player.y + player.height / 5f, player.bodyRotation);
        }
        player.draw(mgg.batch);
        gun.draw(mgg.batch);


        mgg.batch.draw(leftButtonTexture, mgg.camera.position.x - SCR_WIDTH/2 + 30, mgg.camera.position.y - SCR_HEIGHT/2 + 50, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        mgg.batch.draw(rightButtonTexture, mgg.camera.position.x - SCR_WIDTH/2 + 300, mgg.camera.position.y - SCR_HEIGHT/2 + 50, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        mgg.batch.draw(upButtonTexture, mgg.camera.position.x + SCR_WIDTH - SCR_WIDTH/2 - 290, mgg.camera.position.y - SCR_HEIGHT/2 + 50, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        mgg.batch.draw(shootButtonTexture, mgg.camera.position.x + SCR_WIDTH - SCR_WIDTH/2 - 560, mgg.camera.position.y - SCR_HEIGHT/2 + 50, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        mgg.batch.end();

    }
    @Override
    public void show() {

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
    public void dispose () {
        mgg.batch.dispose();
        terrain.dispose();
    }
}

