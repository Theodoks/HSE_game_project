package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextButton {
    float x, y;
    float width, height;
    String text;
    BitmapFont font;

    public TextButton(BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.text = text;

        width = 55;
        height = 55;

        this.x = x;
        this.y = y;
    }

    boolean hit(float tx, float ty) {
        if (x < tx && tx < x + width && y > ty && ty > y - height) {
            return true;
        }
        return false;
    }
}
