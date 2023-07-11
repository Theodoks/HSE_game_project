package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class ScreenGame implements Screen {
    MyGdxGame mgg;
    Texture terrain;
    Texture sky;
    Texture rightButtonTexture, leftButtonTexture, upButtonTexture, shootButtonTexture;
    Texture playerTexture;
    Texture gunTexture;
    Texture eggChildTexture;
    static Texture bullet;
    float gx = 0;
    float gy = 0;
    static Bullet[] playerBullets;
    float gwidth, gheight;


    SolidPlatform[] solids = new SolidPlatform[100];
    Player player;
    Gun gun;
      ArrayList<Object> objects = new ArrayList<>();
    boolean right;
    boolean left;
    boolean up;
    boolean shoot;
    final static int FPS = 60;
    float lerp;
    IconButton rightButton, leftButton, upButton, shootButton;
    Vector3 position;
    Background skyBG;
    long id;
    Sound levelMusic;
    EggChild eggChild;
    EnemyEgg bob;

    ScreenGame(MyGdxGame mgg) {
        this.mgg = mgg;
        //terrain = new Texture("Terrain2.PNG");
        sky = new Texture("Sky2.png");
        bullet = new Texture("bullet2.png");
        rightButtonTexture = new Texture("button_right.png");
        leftButtonTexture = new Texture("button_left.png");
        upButtonTexture = new Texture("button_up.png");
        shootButtonTexture = new Texture(("button_shoot.png"));
        playerTexture = new Texture("egg.png");
        gunTexture = new Texture("egg_gun.png");
        eggChildTexture = new Texture("egg_baby.png");

        playerBullets = new Bullet[100];

        player = new Player(playerTexture, SCR_WIDTH / 12.5f, SCR_HEIGHT / 5, 0, 500, SCR_WIDTH / 190, SCR_HEIGHT / 60, SCR_HEIGHT / 1800);
        gun = new Gun(gunTexture, player.getX(), player.getY(), SCR_WIDTH / 9.5f, SCR_HEIGHT / 15);
        eggChild = new EggChild(eggChildTexture, 1000, 240, SCR_WIDTH / 12.5f, SCR_HEIGHT / 5);

        gwidth = 181.44f * mgg.X;
        gheight = 84.4f * mgg.Y;

        leftButton = new IconButton(SCR_WIDTH / 60, SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, leftButtonTexture);
        rightButton = new IconButton(SCR_WIDTH / 6, SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, rightButtonTexture);
        upButton = new IconButton(SCR_WIDTH - SCR_WIDTH / 5.5f, SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, upButtonTexture);
        shootButton = new IconButton(SCR_WIDTH - SCR_WIDTH / 3, SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, shootButtonTexture);

        //PLATFORMS CREATION START

        for (int i = 0; i < 20; i++) {
            GrassyPlat g = new GrassyPlat(gx, gy, 181.44f * mgg.X, 84.4f * mgg.Y);
            solids[i] = g;
            objects.add(g);
            gx += g.width;

        }
        solids[20] = new GrassyPlat(1000 * mgg.X, mgg.Y * 120, 181.44f * mgg.X, 84.4f * mgg.Y);
        objects.add(solids[20]);
        gx = 500 * mgg.X;
        gy = 202.5f * mgg.Y;
        for (int i = 21; i < 31; i++) {
            GrassyPlat g = new GrassyPlat(gx, gy, 181.44f * mgg.X, 84.4f * mgg.Y);
            solids[i] = g;
            objects.add(g);
            gx += 100 * mgg.X;
            gy += SCR_HEIGHT / 10;
        }
        solids[31] = new Dirt(0, -10000 * mgg . Y, gwidth * 20, 10000 * mgg.Y);
        objects.add(solids[31]);
        solids[32] = new Dirt(-10000 * mgg.X, -10000 * mgg.Y, 10000 * mgg.X, 10400 * mgg.Y);
        objects.add(solids[32]);
        int o = 1;
        for (int i = 33; i < 43; i++) {
            solids[i] = new GrassyPlat(-gwidth * o, 400 * mgg.Y, 181.44f * mgg.X, 84.4f * mgg.Y);
            objects.add(solids[i]);
            o++;
        }

        //PLATFORMS CREATION END
        objects.add(eggChild);

        bob = new EnemyEgg(100, X * 8, 0, Y * 100, false, 200 * X);

        mgg.camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        lerp = 0.12f;
        position = mgg.camera.position;
        skyBG = new Background(sky, mgg);
        levelMusic = Gdx.audio.newSound(Gdx.files.internal("epicMusic2.ogg"));
        id = levelMusic.loop();

    }

    private long start = System.currentTimeMillis();

    public void limitFPS(int fps) {
        if (fps > 0) {
            long diff = System.currentTimeMillis() - start;
            long targetDelay = 1000 / fps;
            if (diff < targetDelay) {
                try {
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            start = System.currentTimeMillis();
        }
    }

    @Override
    public void render(float delta) {
        limitFPS(FPS);

        mgg.batch.begin();
        if (!player.isWinner) {
            if (id == -1) {
                id = levelMusic.loop();
            }
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

            skyBG.exist();


            for (SolidPlatform solid : solids) {
                if (solid != null) {
                    mgg.batch.draw(solid.img, solid.x, solid.y, solid.width, solid.height);
                }


            }


            for (Bullet playerBullet : playerBullets) {
                if (playerBullet != null && playerBullet.doesExist) {
                    playerBullet.exist();
                    mgg.batch.draw(bullet, playerBullet.x, playerBullet.y, SCR_WIDTH / 26.7f, SCR_HEIGHT / 35);
                    playerBullet.collide(objects);
                }

            }
            right = false;
            left = false;
            up = false;
            shoot = false;

            for (int i = 0; i < 3; i++) {


                if (Gdx.input.isTouched(i)) {
                    mgg.touch.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);

                    if (shootButton.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT) && !player.onCD) {
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
            eggChild.draw(mgg.batch);
            player.draw(mgg.batch);
            gun.draw(mgg.batch);
            mgg.batch.setProjectionMatrix(mgg.camera.combined);
            //mgg.camera.position.set(player.x + player.width/2, player.y + player.height/2, 0);
            position.x += (player.x + player.width / 2 - position.x) * lerp;
            position.y += (player.y + player.height / 2 - position.y) * lerp;
            if (shoot) player.shoot(playerBullets);
            player.update(right, left, up, objects);
            bob.update(objects);
            batch.draw(bob.img, bob.x, bob.y, bob.width, bob.height);

            mgg.camera.update();
            if (gun.bodyRotation) {
                gun.update(player.x, player.y + player.height / 5f, player.bodyRotation);
            } else {
                gun.update(player.x + player.getWidth() - gun.getWidth(), player.y + player.height / 5f, player.bodyRotation);
            }

            mgg.batch.draw(leftButtonTexture, mgg.camera.position.x - SCR_WIDTH / 2 + SCR_WIDTH / 60, mgg.camera.position.y - SCR_HEIGHT / 2 + SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
            mgg.batch.draw(rightButtonTexture, mgg.camera.position.x - SCR_WIDTH / 2 + SCR_WIDTH / 6, mgg.camera.position.y - SCR_HEIGHT / 2 + SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
            mgg.batch.draw(upButtonTexture, mgg.camera.position.x + SCR_WIDTH - SCR_WIDTH / 2 - SCR_WIDTH / 5.5f, mgg.camera.position.y - SCR_HEIGHT / 2 + SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
            mgg.batch.draw(shootButtonTexture, mgg.camera.position.x + SCR_WIDTH - SCR_WIDTH / 2 - SCR_WIDTH / 3, mgg.camera.position.y - SCR_HEIGHT / 2 + SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        } else {
            position.set(0.0f, 0.0f, 0.0f);
            mgg.camera.position.set((float) (SCR_WIDTH * 0.5), (float) (SCR_HEIGHT * 0.5), 0f);
            mgg.camera.update();
            levelMusic.stop();
            mgg.setScreen(mgg.screenIntro);
        }

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
    public void dispose() {
        mgg.batch.dispose();
        terrain.dispose();
        sky.dispose();
        shootButtonTexture.dispose();
        leftButtonTexture.dispose();
        rightButtonTexture.dispose();
        upButtonTexture.dispose();
        bullet.dispose();
    }
}

