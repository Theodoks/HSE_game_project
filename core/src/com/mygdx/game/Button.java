package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Button extends Sprite {
    float width, height;
    float x, y;

    Texture buttonTexture;

    public Button(float x, float y, float width, float height, Texture buttonTexture) {
        super(buttonTexture, 0, 0, buttonTexture.getWidth(), buttonTexture.getHeight());
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.buttonTexture = buttonTexture;

        setSize(width, height);
        setPosition(x, y);
    }

    boolean hit(float tx, float ty) {
        if (x < tx && tx < x + width && y > ty && ty > y - height) {
            return true;
        }
        return false;
    }

    ;
}
