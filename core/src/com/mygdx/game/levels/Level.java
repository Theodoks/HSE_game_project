package com.mygdx.game.levels;

import static com.mygdx.game.MyGdxGame.FPS;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;
import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Background;
import com.mygdx.game.Bullet;
import com.mygdx.game.EggChild;
import com.mygdx.game.EnemyBullet;
import com.mygdx.game.EnemyEgg;
import com.mygdx.game.Gun;
import com.mygdx.game.IconButton;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;
import com.mygdx.game.SolidPlatform;
import com.mygdx.game.Text;
import com.mygdx.game.TextButton;
import com.mygdx.game.animations.BulletImpactAnim;

import java.util.ArrayList;

public class Level implements Screen {

    Gun gun;
    MyGdxGame mgg;
    IconButton rightButton, leftButton, upButton, shootButton;
    Vector3 position;
    Background skyBG;

    protected Texture bullet;

    static Bullet[] playerBullets;


    protected EggChild eggChild;
    EnemyEgg bob;

    protected Texture terrain;
    Texture eggChildTexture;

    protected Texture enemyEggTexture;
    boolean right;
    boolean left;
    boolean up;
    boolean shoot;
    Text hpBar;

    Texture sky;
    Texture rightButtonTexture, leftButtonTexture, upButtonTexture, shootButtonTexture;
    TextButton btnReturn;
    protected Player player;

    float lerp;

    Texture gunTexture;

    Texture playerTexture;

    protected SolidPlatform[] solids = new SolidPlatform[100];

    protected ArrayList<Object> objects = new ArrayList<>();

    protected ArrayList<EnemyEgg> enemyEggs = new ArrayList<>();

    // do not delete static
    static public  ArrayList<BulletImpactAnim> bullAnims = new ArrayList<>();

    private long start = System.currentTimeMillis();
    public BitmapFont hpFont;

    protected Level(MyGdxGame mgg) {
        this.mgg = mgg;

        rightButtonTexture = new Texture("button_right.png");
        leftButtonTexture = new Texture("button_left.png");
        upButtonTexture = new Texture("button_up.png");
        shootButtonTexture = new Texture(("button_shoot.png"));
        playerTexture = new Texture("egg.png");
        gunTexture = new Texture("egg_gun.png");
        sky = new Texture("Sky2.png");
        bullet = new Texture("bullet2.png");
        eggChildTexture = new Texture("egg_baby.png");
        enemyEggTexture = new Texture("egg_enemy.png");

        createFont();

        playerBullets = new Bullet[100];

        leftButton = new IconButton(SCR_WIDTH / 60, SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, leftButtonTexture);
        rightButton = new IconButton(SCR_WIDTH / 6, SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, rightButtonTexture);
        upButton = new IconButton(SCR_WIDTH - SCR_WIDTH / 5.5f, SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, upButtonTexture);
        shootButton = new IconButton(SCR_WIDTH - SCR_WIDTH / 3, SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, shootButtonTexture);
        btnReturn = new TextButton(mgg.font, "return", SCR_WIDTH * 0.05f, SCR_HEIGHT * 0.95f, 55 * mgg.X, 55 * mgg.Y);

        position = mgg.camera.position;
        skyBG = new Background(sky, mgg);

        player = new Player(playerTexture, 3, SCR_WIDTH / 15f, SCR_HEIGHT / 6f, 0, 400 * Y, SCR_WIDTH / 190, 14.5f * Y, SCR_HEIGHT / 1800);
        objects.add(player);
        gun = new Gun(gunTexture, player.getX(), player.getY(), SCR_WIDTH / 9.5f, SCR_HEIGHT / 15);
        eggChild = new EggChild(eggChildTexture, 1000 * X, 500 * Y, SCR_WIDTH / 25f, SCR_HEIGHT / 10);

        bullet = new Texture("bullet2.png");
        eggChildTexture = new Texture("egg_baby.png");
        enemyEggTexture = new Texture("egg_enemy.png");

        mgg.camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        lerp = 0.12f;
        hpBar = new Text(hpFont, "HP: " + player.hp, 10 * X, 750 * Y);


    }

    void render() {
        if (mgg.startMusic == -1) {
            mgg.startMusic = mgg.levelMusic.loop();

        }


        skyBG.exist();
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
                if(btnReturn.hit(mgg.touch.x, -mgg.touch.y + SCR_HEIGHT)){
                    player.dead = true;
                }
            }
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

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
        for (EnemyEgg enemyEgg : enemyEggs){

            enemyEgg.update(objects, player);
            enemyEgg.draw(mgg.batch);
            for (EnemyBullet enemyBullet : enemyEgg.enemyBullets) {
                if (enemyBullet != null && enemyBullet.doesExist) {
                    enemyBullet.exist();
                    mgg.batch.draw(bullet, enemyBullet.x, enemyBullet.y, SCR_WIDTH / 26.7f, SCR_HEIGHT / 35);
                    enemyBullet.collide(objects);

                }
            }
        }
        btnReturn.font.draw(mgg.batch, btnReturn.text, mgg.camera.position.x - SCR_WIDTH / 2 + SCR_WIDTH * 0.05f, mgg.camera.position.y - SCR_HEIGHT / 2  + SCR_HEIGHT * 0.95f);
        mgg.batch.draw(leftButtonTexture, mgg.camera.position.x - SCR_WIDTH / 2 + SCR_WIDTH / 60, mgg.camera.position.y - SCR_HEIGHT / 2 + SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        mgg.batch.draw(rightButtonTexture, mgg.camera.position.x - SCR_WIDTH / 2 + SCR_WIDTH / 6, mgg.camera.position.y - SCR_HEIGHT / 2 + SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        mgg.batch.draw(upButtonTexture, mgg.camera.position.x + SCR_WIDTH - SCR_WIDTH / 2 - SCR_WIDTH / 5.5f, mgg.camera.position.y - SCR_HEIGHT / 2 + SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        mgg.batch.draw(shootButtonTexture, mgg.camera.position.x + SCR_WIDTH - SCR_WIDTH / 2 - SCR_WIDTH / 3, mgg.camera.position.y - SCR_HEIGHT / 2 + SCR_WIDTH / 40, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);

        eggChild.draw(mgg.batch);
        player.draw(mgg.batch);
        gun.draw(mgg.batch);

        for (BulletImpactAnim bulletImpactAnim : bullAnims){
            if (bulletImpactAnim.doesExist){
                bulletImpactAnim.exist();
                mgg.batch.draw(bulletImpactAnim.textures[bulletImpactAnim.count], bulletImpactAnim.x, bulletImpactAnim.y, bulletImpactAnim.width, bulletImpactAnim.height);
            }

        }

        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        //mgg.camera.position.set(player.x + player.width/2, player.y + player.height/2, 0);
        position.x += (player.x + player.width / 2 - position.x) * lerp;
        position.y += (player.y + player.height / 2 - position.y) * lerp;
        if (shoot) player.shoot(playerBullets);
        player.update(right, left, up, objects);



        mgg.camera.update();
        if (gun.bodyRotation) {
            gun.update(player.x, player.y + player.height / 5f, player.bodyRotation);
        } else {
            gun.update(player.x + player.getWidth() - gun.getWidth(), player.y + player.height / 5f, player.bodyRotation);
        }


    }

    void win() {
        mgg.maxLevel = -1;
    }

    @Override
    public void show() {

    }

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

        if(!player.isWinner && !player.dead) {
            render();
        } else if (player.isWinner) {
            position.set(0.0f, 0.0f, 0.0f);
            mgg.camera.position.set((float) (SCR_WIDTH * 0.5), (float) (SCR_HEIGHT * 0.5), 0f);
            mgg.camera.update();
            win();
            mgg.setScreen(mgg.screenVictory);
        } else if (player.dead) {
            position.set(0.0f, 0.0f, 0.0f);
            mgg.camera.position.set((float) (SCR_WIDTH * 0.5), (float) (SCR_HEIGHT * 0.5), 0f);
            mgg.camera.update();
            mgg.setScreen(mgg.screenLevels);
            mgg.setScreen(mgg.screenDefeat);
        }
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
        sky.dispose();
        shootButtonTexture.dispose();
        leftButtonTexture.dispose();
        rightButtonTexture.dispose();
        upButtonTexture.dispose();
        playerTexture.dispose();
        gunTexture.dispose();
    }

    void createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("wellwait.otf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

        parameter.size = (int)(50 * mgg.A);
        parameter.color = Color.GREEN;
        parameter.borderWidth = mgg.A * 3;
        parameter.borderColor = Color.BLACK;
        hpFont = generator.generateFont(parameter);


    }
}
