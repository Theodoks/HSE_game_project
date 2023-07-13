package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class ScreenDefeat implements Screen{
    MyGdxGame mgg;

    Texture imgBackGround;
    Texture toLevelsButtonTexture;
    Texture tryAgainButtonTexture;

    BitmapFont font;

    IconButton toLevelsButton;
    IconButton tryAgainButton;

    Text victoryText;

    int levelIndex;


    public ScreenDefeat(MyGdxGame g, int levelIndex) {
        mgg = g;
        this.levelIndex = levelIndex;

        createFont();

        imgBackGround = new Texture("Sky.jpg");
        toLevelsButtonTexture = new Texture("toLevelsButton.png");
        tryAgainButtonTexture = new Texture("tryAgainButton.png");
        victoryText = new Text(font, "YOU ARE DEAD", mgg.X * 80, SCR_HEIGHT * 0.8f);
        toLevelsButton = new IconButton(SCR_WIDTH * 0.3333f, SCR_HEIGHT * 0.15f, SCR_WIDTH / 7 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 7, toLevelsButtonTexture);
        tryAgainButton = new IconButton(SCR_WIDTH * 0.6666f, SCR_HEIGHT * 0.15f, SCR_WIDTH / 7 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 7, tryAgainButtonTexture);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            mgg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mgg.camera.unproject(mgg.touch);
            if(toLevelsButton.hit(mgg.touch.x, mgg.touch.y)){
                mgg.setScreen(mgg.screenLevels);
            }
            else if (tryAgainButton.hit(mgg.touch.x, mgg.touch.y)) {
                mgg.loadLevel(levelIndex);
            }
        }

        mgg.camera.update();
        mgg.batch.begin();
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        toLevelsButton.draw(mgg.batch);
        tryAgainButton.draw(mgg.batch);

        victoryText.font.draw(mgg.batch, victoryText.text, victoryText.x, victoryText.y);

        mgg.batch.end();
    }

    void createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("LilitaOne-Regular.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

        parameter.size = (int)(220 * mgg.A);
        parameter.color = Color.ORANGE;
        parameter.borderWidth = (int)(10 * mgg.A);
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
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

