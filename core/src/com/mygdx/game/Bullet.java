package com.mygdx.game;



public class Bullet {
    float x = Player.x;
    float y = Player.y;
    float vx = 14;
    Boolean doesExist = true;
    public void exist(){
        if (doesExist){
            MyGdxGame.batch.draw(MyGdxGame.bullet, x, y + (MyGdxGame.SCR_HEIGHT/14.85f), MyGdxGame.SCR_WIDTH/15, MyGdxGame.SCR_HEIGHT/25);
            x += vx;
        }
    }

}
