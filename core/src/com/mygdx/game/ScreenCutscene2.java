package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ScreenCutscene2 implements Screen {

    private MyGdxGame mgg;
    private SpriteBatch batch;
    private TextureRegion[] images;
    private int currentIndex;
    private float elapsedTime;
    private float transitionTime;
    private boolean isTransitioning;

    public ScreenCutscene2(MyGdxGame mgg) {
        this.mgg = mgg;
        this.batch = mgg.batch;

        // Загрузка трех изображений
        Texture egg1 = new Texture(Gdx.files.internal("eggFinal1.png"));
        Texture egg2 = new Texture(Gdx.files.internal("eggFinal2.png"));
        Texture egg3 = new Texture(Gdx.files.internal("eggFinal3.png"));

        images = new TextureRegion[3];
        images[0] = new TextureRegion(egg1);
        images[1] = new TextureRegion(egg2);
        images[2] = new TextureRegion(egg3);

        currentIndex = 0;
        elapsedTime = 0;
        transitionTime = 0.5f;
        isTransitioning = false;
    }

    @Override
    public void render(float delta) {
        // Очистка экрана перед каждой отрисовкой
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Начало отрисовки спрайтов
        batch.begin();

        // Отрисовка текущего изображения
        batch.draw(images[currentIndex], 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Если мы находимся в процессе перехода, отрисовываем текущее и следующее изображения с анимацией
        if (isTransitioning) {
            float alpha = elapsedTime / transitionTime;
            batch.setColor(1f, 1f, 1f, alpha);

            int nextIndex = (currentIndex + 1) % images.length;
            batch.draw(images[nextIndex], 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            // Обновляем таймер и проверяем, закончился ли переход
            elapsedTime += delta;
            if (elapsedTime >= transitionTime) {
                isTransitioning = false;
                currentIndex = nextIndex;
                elapsedTime = 0;
            }

            batch.setColor(1f, 1f, 1f, 1f);
        }

        // Завершение отрисовки спрайтов
        batch.end();

        // Обновление таймера и проверка, нужно ли начать следующий переход
        elapsedTime += delta;
        if (!isTransitioning && elapsedTime >= 3f) {
            isTransitioning = true;
            elapsedTime = 0;
        }
        if (currentIndex == 2 && elapsedTime >= 2.9f) {
            mgg.setScreen(mgg.screenLevels);
            currentIndex = 0;
            elapsedTime = 0;
        }
    }

    @Override
    public void dispose() {
        // Освобождение ресурсов
        batch.dispose();
        for (TextureRegion image : images) {
            image.getTexture().dispose();
        }
    }

    // Остальные методы интерфейса Screen
    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}
