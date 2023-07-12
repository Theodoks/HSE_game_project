package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelButton extends Sprite {

    float x, y;
    private BitmapFont font;
    private String levelNumber;
    private float width;
    private float height;

    public LevelButton(float x, float y, Texture texture, BitmapFont font, String levelNumber, float width, float height) {
        super(texture);
        this.x = x;
        this.y = y;
        this.font = font;
        this.levelNumber = levelNumber;
        this.width = width;
        this.height = height;
        setSize(width, height);
        setPosition(x, y);
    }

    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        font.draw(spriteBatch, levelNumber, getX() + getWidth() / 2 - font.getXHeight() / 2, getY() + getHeight() / 2 + font.getCapHeight() / 2);
    }

    boolean hit(float tx, float ty) {
        return (x < tx && tx < (x + width)) && (y < ty && ty < (y + height));
    }
}
