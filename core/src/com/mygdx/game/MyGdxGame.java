package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.levels.Level;
import com.mygdx.game.levels.Level0;
import com.mygdx.game.levels.Level1;
import com.mygdx.game.levels.Level2;
import com.mygdx.game.levels.Level3;

public class MyGdxGame extends Game {
    public static float SCR_WIDTH;
    public static float SCR_HEIGHT;
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;

    public ScreenIntro screenIntro;
    public ScreenAbout screenAbout;
    public Sound levelMusic;
    public long startMusic;

    public ScreenVictory screenVictory;
    public ScreenDefeat screenDefeat;

    public final static int FPS = 60;
    Level level;
    public ScreenLevels screenLevels;
    public static float X;
    public static float Y;
    public static float A;
    public int maxLevel;
    public Preferences pref;
    @Override
    public void create() {
        pref = Gdx.app.getPreferences("EGG");
        maxLevel = pref.getInteger("maxLevel", 0);
        batch = new SpriteBatch();
        touch = new Vector3(0, 0 ,0);
        SCR_HEIGHT = Gdx.graphics.getHeight();
        SCR_WIDTH = Gdx.graphics.getWidth();
        X = SCR_WIDTH / 1633;
        Y = SCR_HEIGHT / 810;
        A = (X + Y) / 2;
        createFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        screenIntro = new ScreenIntro(this);
        screenAbout = new ScreenAbout(this);
        screenLevels = new ScreenLevels(this);
        screenVictory = new ScreenVictory(this, 0);
        screenDefeat = new ScreenDefeat(this, 0);
        setScreen(screenIntro);
        levelMusic = Gdx.audio.newSound(Gdx.files.internal("epicMusic2.ogg"));
        startMusic = -1;

    }
    void createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("LilitaOne-Regular.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

        parameter.size = (int)(50 * A);
        parameter.color = Color.ORANGE;
        parameter.borderWidth = A * 3;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);


    }
    public void loadLevel(int levelid){
        if(levelid == 0) {
            level = new Level0(this);
            setScreen(level);
        }
        else if(levelid == 1)
        {
            level = new Level1(this);
            setScreen(level);
        }
        else if(levelid == 2)
        {
            level = new Level2(this);
            setScreen(level);
        }
        else if(levelid == 3)
        {
            level = new Level3(this);
            setScreen(level);
        }
    }
}
