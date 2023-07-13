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

    Texture cutsceneButtonTexture;

    IconTextButton[] levelButtons;
    IconButton cutsceneButton;

    BitmapFont font;

    TextButton btnReturn;


    public ScreenLevels(MyGdxGame g) {
        mgg = g;

        levelButtonTexture = new Texture("oval.png");
        imgBackGround = new Texture("Sky.jpg");
        cutsceneButtonTexture = new Texture("button_cinema.png");
        btnReturn = new TextButton(mgg.font, "Back", SCR_WIDTH * 0.05f, SCR_HEIGHT * 0.95f, 55 * mgg.X, 55 * mgg.Y);

        createFont();

        cutsceneButton = new IconButton(SCR_WIDTH * 0.48f, SCR_HEIGHT * 0.1f, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4, cutsceneButtonTexture);

        levelButtons = new IconTextButton[4];
        for (int i = 0; i < levelButtons.length; i++) {
            levelButtons[i] = new IconTextButton(
                    SCR_WIDTH*0.15f + i * (SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT) * mgg.A + 100),
                    SCR_HEIGHT / 2 - SCR_HEIGHT / 4 * mgg.A / 2,
                    levelButtonTexture,
                    font,
                    "" + (i+1),
                    SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT) * mgg.A,
                    SCR_HEIGHT / 4 * mgg.A
            );
        }
    }

    void createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("LilitaOne-Regular.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

        parameter.size = (int)(110 * mgg.A);
        parameter.color = Color.ORANGE;
        parameter.borderWidth = (int)(7 * mgg.A);
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
             else if (levelButtons[0].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 0) {
                mgg.loadLevel(0);
                mgg.screenDefeat.levelIndex = 0;
                mgg.screenVictory.levelIndex = 0;
            }
             else if (levelButtons[1].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 1) {
                mgg.loadLevel(1);
                mgg.screenDefeat.levelIndex = 1;
                mgg.screenVictory.levelIndex = 1;
            }
            else if (levelButtons[2].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 2) {
                mgg.loadLevel(2);
                mgg.screenDefeat.levelIndex = 2;
                mgg.screenVictory.levelIndex = 2;
            }
            else if (levelButtons[3].hit(mgg.touch.x, mgg.touch.y) && mgg.maxLevel >= 2) {
                mgg.loadLevel(3);
                mgg.screenDefeat.levelIndex = 3;
                mgg.screenVictory.levelIndex = 2;
            } else if (cutsceneButton.hit(mgg.touch.x, mgg.touch.y)) {
                mgg.setScreen(mgg.screenCutscene2);
            }
        }

        mgg.camera.update();
        mgg.batch.begin();
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        mgg.batch.draw(cutsceneButton, SCR_WIDTH * 0.48f, SCR_HEIGHT * 0.1f, SCR_WIDTH / 4 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 4);

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
