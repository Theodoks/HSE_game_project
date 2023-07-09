package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;


public class Background {
    Texture img;
    MyGdxGame mgg;

    float x1, x2, y1, y2;
    float leftBGx, rightBGx; //, centerBGx;
    float hold;

    Background(Texture img, MyGdxGame mgg){
        this.img = img;
        this.mgg = mgg;
        x1 = mgg.camera.position.x - MyGdxGame.SCR_WIDTH;
        x2 = mgg.camera.position.x;
        //x3 = mgg.camera.position.x + MyGdxGame.SCR_WIDTH / 2;

    }

    public void exist(){

        y1 = y2 = mgg.camera.position.y - MyGdxGame.SCR_HEIGHT / 2;

        if(x1 < mgg.camera.position.x - MyGdxGame.SCR_WIDTH * 3 / 2){
            x1 = x2 + MyGdxGame.SCR_WIDTH;

        }
        if(x2 < mgg.camera.position.x - MyGdxGame.SCR_WIDTH * 3 / 2){
            x2 = x1 + MyGdxGame.SCR_WIDTH;

        }
        if(x1 > mgg.camera.position.x + MyGdxGame.SCR_WIDTH / 2){
            x1 = x2 - MyGdxGame.SCR_WIDTH;

        }
        if(x2 > mgg.camera.position.x + MyGdxGame.SCR_WIDTH / 2){
            x2 = x1 - MyGdxGame.SCR_WIDTH;

        }

        mgg.batch.draw(img, x1, y1, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
        mgg.batch.draw(img, x2, y2, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
        //mgg.batch.draw(img, x3, y3, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
    }
}
