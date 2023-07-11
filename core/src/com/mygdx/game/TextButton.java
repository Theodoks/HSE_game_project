package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import static com.mygdx.game.MyGdxGame.X;
import static com.mygdx.game.MyGdxGame.Y;
import static com.mygdx.game.MyGdxGame.A;

public class TextButton {
    float x, y;
    float width, height;
    String text;
    BitmapFont font;

    public TextButton(BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.text = text;

        width = 55 * X;
        height = 55 * Y;

        this.x = x;
        this.y = y;
    }

    boolean hit(float tx, float ty) {
        if (x < tx && tx < x + width * text.length() && y > ty && ty > y - height) {
            return true;
        }
        return false;
    }
}
