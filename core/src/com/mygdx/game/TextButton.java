package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextButton {
    float x, y;
    float width, height;
    String text;
    BitmapFont font;

    public TextButton(BitmapFont font, String text, float x, float y, float width, float height) {
        this.font = font;
        this.text = text;

        this.width = width;
        this.height = height;

        this.x = x;
        this.y = y;
    }

    boolean hit(float tx, float ty) {
        return x < tx && tx < x + width * text.length() && y > ty && ty > y - height;
    }
}
