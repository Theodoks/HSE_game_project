package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.IconButton;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Text;

public class ScreenVictory implements Screen {

    MyGdxGame mgg;

    Texture imgBackGround;
    Texture toLevelsButtonTexture;
    Texture nextLevelButtonTexture;

    BitmapFont font;

    IconButton toLevelsButton;
    IconButton nextLevelButton;

    Text victoryText;

    int levelIndex;


    public ScreenVictory(MyGdxGame g, int levelIndex) {
        mgg = g;
        this.levelIndex = levelIndex;

        createFont();

        imgBackGround = new Texture("Sky.jpg");
        toLevelsButtonTexture = new Texture("toLevelsButton.png");
        nextLevelButtonTexture = new Texture("nextLevelButton.png");
        victoryText = new Text(font, "VICTORY", SCR_WIDTH * 0.23f, SCR_HEIGHT * 0.8f);
        toLevelsButton = new IconButton(SCR_WIDTH * 0.3333f, SCR_HEIGHT * 0.15f, SCR_WIDTH / 7 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 7, toLevelsButtonTexture);
        nextLevelButton = new IconButton(SCR_WIDTH * 0.6666f, SCR_HEIGHT * 0.15f, SCR_WIDTH / 7 / (SCR_WIDTH / SCR_HEIGHT), SCR_HEIGHT / 7, nextLevelButtonTexture);
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
            else if (nextLevelButton.hit(mgg.touch.x, mgg.touch.y)) {
                if(levelIndex < 5) {
                    mgg.loadLevel(levelIndex + 1);
                    mgg.screenDefeat.levelIndex = levelIndex + 1;
                    levelIndex += 1;
                }
                else{
                    mgg.setScreen(mgg.screenCutscene2);
                }
            }
        }

        mgg.camera.update();
        mgg.batch.begin();
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        toLevelsButton.draw(mgg.batch);
        nextLevelButton.draw(mgg.batch);

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
