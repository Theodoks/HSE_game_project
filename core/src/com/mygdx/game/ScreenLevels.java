package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class ScreenLevels implements Screen {

    MyGdxGame mgg;

    Texture imgBackGround;

    Texture levelButtonTexture;

    LevelButton[] levelButtons;

    BitmapFont font;

    TextButton btnReturn;


    public ScreenLevels(MyGdxGame g) {
        mgg = g;

        levelButtonTexture = new Texture("oval.png");
        imgBackGround = new Texture("Sky.jpg");
        btnReturn = new TextButton(mgg.font, "return", SCR_WIDTH * 0.05f, SCR_HEIGHT * 0.95f, 55 * mgg.X, 55 * mgg.Y);

        createFont();


        levelButtons = new LevelButton[4];
        for (int i = 0; i < levelButtons.length; i++) {
            levelButtons[i] = new LevelButton(
                    SCR_WIDTH*0.15f + i * (SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT) * mgg.A + 100),
                    SCR_HEIGHT / 2 - SCR_HEIGHT / 4 * mgg.A / 2,
                    levelButtonTexture,
                    font,
                    "" + (i+1),
                    SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT) * mgg.A,
                    SCR_HEIGHT / 4 * mgg.A
            );


//            LevelButton levelButton = new LevelButton(levelButtonTexture, font, String.valueOf(i + 1)); // создаем кнопку
//            levelButton.setPosition(startX + i * (buttonWidth + spacing), SCR_HEIGHT / 2 - buttonHeight / 2); // устанавливаем позицию кнопки
//            levelButtons[i] = levelButton; // добавляем кнопку в массив
        }
    }

    void createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("wellwait.otf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

        parameter.size = (int)(110 * mgg.A);
        parameter.color = Color.ORANGE;
        parameter.borderWidth = (int)(3 * mgg.A);
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            mgg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mgg.camera.unproject(mgg.touch);
            if(btnReturn.hit(mgg.touch.x, mgg.touch.y)){
                mgg.setScreen(mgg.screenIntro);
            }
             else if (levelButtons[0].hit(mgg.touch.x, mgg.touch.y)) {
                mgg.createGame(1);
                mgg.setScreen(mgg.level2);
            }
        }

        mgg.camera.update();
        mgg.batch.begin();
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        btnReturn.font.draw(mgg.batch, btnReturn.text, btnReturn.x, btnReturn.y);

        for (int i = 0; i < levelButtons.length; i++) {
            levelButtons[i].draw(mgg.batch);
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

    }
}
