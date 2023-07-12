package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Text {
    float x;
    float y;
    static float width;
    static float height;
    String text;
    BitmapFont font;

    public Text(BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.text = text;

        width = 55;
        height = 55;

        this.x = x;
        this.y = y;
    }

}
