package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenAbout implements Screen {
    MyGdxGame mgg;

    Texture imgBackGround;

    TextButton btnReturn;
    Text aboutUs;

    public ScreenAbout(MyGdxGame g) {
        mgg = g;

        imgBackGround = new Texture("Sky.jpg");
        btnReturn = new TextButton(mgg.font, "return", SCR_WIDTH * 0.05f, SCR_HEIGHT * 0.95f);
        aboutUs = new Text(mgg.font, "Красноперов Данил\nФёдор Переверзев\nМуслим Костоев\nМансур Костоев", SCR_WIDTH * 0.15f, SCR_HEIGHT * 0.8f);
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
        }

        mgg.camera.update();
        mgg.batch.setProjectionMatrix(mgg.camera.combined);
        mgg.batch.begin();
        mgg.batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        btnReturn.font.draw(mgg.batch, btnReturn.text, btnReturn.x, btnReturn.y);
        aboutUs.font.draw(mgg.batch, aboutUs.text, aboutUs.x, aboutUs.y);
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