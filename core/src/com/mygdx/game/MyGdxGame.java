package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends Game {
    static float SCR_WIDTH;
    static float SCR_HEIGHT;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont font;
    ScreenIntro screenIntro;
    ScreenGame screenGame;
    ScreenAbout screenAbout;

    ScreenLevels screenLevels;
    public float X;
    public float Y;
    public float A;
    @Override
    public void create() {
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
        screenGame = new ScreenGame(this);
        screenAbout = new ScreenAbout(this);
        screenLevels = new ScreenLevels(this);
        setScreen(screenIntro);

    }
    void createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("wellwait.otf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

        parameter.size = (int)(50 * A);
        parameter.color = Color.ORANGE;
        parameter.borderWidth = A * 3;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
    }

}
