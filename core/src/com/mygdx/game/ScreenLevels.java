package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;

public class ScreenLevels implements Screen {

    MyGdxGame mgg;

    Texture imgBackGround;

    Texture levelButtonTexture;

    Texture cutscene2ButtonTexture;
    Texture cutscene1ButtonTexture;

    IconTextButton[] levelButtons;
    IconButton cutscene2Button;
    IconButton cutscene1Button;

    BitmapFont font;

    TextButton btnReturn;


    public ScreenLevels(MyGdxGame g) {
        mgg = g;

        levelButtonTexture = new Texture("oval.png");
        imgBackGround = new Texture("Sky.jpg");
        cutscene2ButtonTexture = new Texture("button_cinema.png");
        cutscene1ButtonTexture = new Texture("button_cinema.png");
        btnReturn = new TextButton(mgg.font, "Back", SCR_WIDTH * 0.05f, SCR_HEIGHT * 0.95f, 55 * mgg.X, 55 * mgg.Y);

        createFont();


        levelButtons = new IconTextButton[5];
        for (int i = 0; i < levelButtons.length; i++) {
            levelButtons[i] = new IconTextButton(
                    SCR_WIDTH * 0.1f + i * (SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT) * mgg.A + 100),
                    SCR_HEIGHT / 2 - SCR_HEIGHT / 4 * mgg.A / 2,
                    levelButtonTexture,
                    font,
                    "" + (i + 1),
                    SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT) * mgg.A,
                    SCR_HEIGHT / 4 * mgg.A
            );
        }
        cutscene1Button = new IconButton(levelButtons[0].x, levelButtons[0].y - 250 * Y, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, cutscene2ButtonTexture);
        cutscene2Button = new IconButton(levelButtons[4].x, levelButtons[4].y - 250 * Y, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, cutscene2ButtonTexture);

    }

    void createFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("LilitaOne-Regular.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

        parameter.size = (int) (110 * mgg.A);
        parameter.color = Color.ORANGE;
        parameter.borderWidth = (int) (7 * mgg.A);
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            mgg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mgg.camera.unproject(mgg.touch);
            if (btnReturn.hit(mgg.touch.x, mgg.touch.y)) {
                mgg.setScreen(mgg.screenIntro);
            } else if (levelButtons[0].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 0) {
                mgg.loadLevel(0);
                mgg.screenDefeat.levelIndex = 0;
                mgg.screenVictory.levelIndex = 0;
            } else if (levelButtons[1].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 1) {
                mgg.loadLevel(1);
                mgg.screenDefeat.levelIndex = 1;
                mgg.screenVictory.levelIndex = 1;
            } else if (levelButtons[2].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 2) {
                mgg.loadLevel(2);
                mgg.screenDefeat.levelIndex = 2;
                mgg.screenVictory.levelIndex = 2;
            } else if (levelButtons[3].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 3) {
                mgg.loadLevel(3);
                mgg.screenDefeat.levelIndex = 3;
                mgg.screenVictory.levelIndex = 3;
            } else if (levelButtons[3].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 3) {
                mgg.loadLevel(4);
                mgg.screenDefeat.levelIndex = 4;
                mgg.screenVictory.levelIndex = 3;
            } else if (cutscene2Button.hit(mgg.touch.x, mgg.touch.y)) {
                mgg.setScreen(mgg.screenCutscene2);
            } else if (cutscene1Button.hit(mgg.touch.x, mgg.touch.y)) {
                mgg.setScreen(mgg.screenCutscene1);
            }
        }

        mgg.camera.update();
        mgg.batch.begin();
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        mgg.batch.draw(cutscene2Button, levelButtons[0].x, levelButtons[0].y - 250 * Y, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);
        mgg.batch.draw(cutscene2Button, levelButtons[4].x, levelButtons[4].y - 250 * Y, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);

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
