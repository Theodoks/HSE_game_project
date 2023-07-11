package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenLevels implements Screen {

    MyGdxGame mgg;

    Texture imgBackGround;
    
    TextButton[] levelsButtons;

    TextButton btnReturn;

    public ScreenLevels(MyGdxGame g) {
        mgg = g;

        imgBackGround = new Texture("Sky.jpg");
        btnReturn = new TextButton(mgg.font, "return", SCR_WIDTH * 0.05f, SCR_HEIGHT * 0.95f, 55 * mgg.X, 55 * mgg.Y);

        levelsButtons = new TextButton[4];
        for (int i = 0; i < levelsButtons.length; i++) {
            levelsButtons[i] = new TextButton(mgg.font, "" + (i+1), SCR_WIDTH * (0.2f*(i+1)), SCR_HEIGHT * 0.75f, 55 * mgg.X, 55 * mgg.Y);
        }
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
            } else if (levelsButtons[0].hit(mgg.touch.x, mgg.touch.y)) {
                mgg.setScreen(mgg.screenGame);
            }
        }

        mgg.camera.update();
        mgg.batch.begin();
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        btnReturn.font.draw(mgg.batch, btnReturn.text, btnReturn.x, btnReturn.y);
        for (int i = 0; i < levelsButtons.length; i++) {
            levelsButtons[i].font.draw(mgg.batch, levelsButtons[i].text, levelsButtons[i].x, levelsButtons[i].y);
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
