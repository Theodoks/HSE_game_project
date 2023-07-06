package com.mygdx.game;

public class Bullet {
    float x = Player.x;
    float y = Player.y;
    float vx = 7;
    Boolean doesExist = true;
    public void exist(){
        if (doesExist){
            MyGdxGame.batch.draw(MyGdxGame.bullet, x, y );
            x += vx;
        }


    }
}
