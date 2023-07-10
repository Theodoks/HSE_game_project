package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Objects;

public class MyGdxGame extends Game {
    static float SCR_WIDTH;
    static float SCR_HEIGHT;
    static SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    Vector3 touch2;
    BitmapFont font;
    ScreenIntro screenIntro;
    ScreenGame screenGame;
    ScreenAbout screenAbout;

    ScreenLevels screenLevels;

    @Override
    public void create() {
        batch = new SpriteBatch();
        touch = new Vector3(0, 0 ,0);
        createFont();
        SCR_WIDTH = Gdx.graphics.getWidth();
        SCR_HEIGHT = Gdx.graphics.getHeight();
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

        parameter.size = 50;
        parameter.color = Color.ORANGE;
        parameter.borderWidth = 3;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
    }

}
