package com.mygdx.game.animations;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class BulletImpactAnim {
    public Texture[] textures;
    public int count;
    public float x, y, width, height;
    public boolean doesExist;

    MyGdxGame mgg;

    public BulletImpactAnim(float x, float y, float width, float height){
        textures = new Texture[7];
        for (int i = 0; i <= 6; i++) {
            textures[i] = new Texture("Exp" + (i+1) + ".png");
        }
        count = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        doesExist = true;

    }
    public void exist(){
        if(count < 6){
            count++;
            //mgg.batch.draw(textures[count], x, y, width, height);
        }
        else{

            doesExist = false;
        }
    }


}
