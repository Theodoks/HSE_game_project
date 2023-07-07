package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Button {
    float x, y;
    float width, height;

    Texture buttonTexture;

    public Button(float x, float y, Texture buttonTexture) {
        this.x = x;
        this.y = y;

        width = 200;
        height = 200;

        this.buttonTexture = buttonTexture;
    }

    boolean hit(float tx, float ty) {
        if (x < tx && tx < x + width && y > ty && ty > y - height) {
            return true;
        }
        return false;
    };
}
