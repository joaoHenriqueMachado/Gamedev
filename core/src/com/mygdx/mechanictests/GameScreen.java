package com.mygdx.mechanictests;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mechanictests.enemy.EnemyController;
import com.mygdx.mechanictests.projectile.ProjectileController;
import com.mygdx.mechanictests.ship.Ship;

public class GameScreen implements Screen {
    // screen
    final private Camera camera;
    final private Viewport viewport;

    // graphics
    final private SpriteBatch batch;
    final private Texture[] backgrounds;

    // timing
    final private float[] backgroundOffsets = {0,0,0,0};
    final private float backgroundMaxScrollingSpeed;

    public static int WORLD_WIDTH = 3840;
    public static int WORLD_HEIGHT = 2160;

    public static Ship ship;

    public static int score;

    public static int counter;
    public GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        backgrounds = new Texture[4];

        backgrounds[0] = new Texture("Starscape00.png");
        backgrounds[1] = new Texture("Starscape01.png");
        backgrounds[2] = new Texture("Starscape01.png");
        backgrounds[3] = new Texture("Starscape03.png");

        backgroundMaxScrollingSpeed = (float)(WORLD_HEIGHT) / 16;
        batch = new SpriteBatch();
        ship = new Ship();
        ProjectileController.init();
        EnemyController.init();
        counter = 0;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        renderBackground(delta);
        ship.draw(batch, delta);
        ProjectileController.draw(batch, delta);
        EnemyController.draw(batch, delta);
        batch.end();
        if(counter >= 20){
            EnemyController.set((float)GameScreen.WORLD_WIDTH/2, GameScreen.WORLD_HEIGHT);
            counter = 0;
        }
        counter++;
    }
    private void renderBackground(float deltaTime){
        backgroundOffsets[0] += deltaTime*backgroundMaxScrollingSpeed/8;
        backgroundOffsets[1] += deltaTime*backgroundMaxScrollingSpeed/4;
        backgroundOffsets[2] += deltaTime*backgroundMaxScrollingSpeed/2;
        backgroundOffsets[3] += deltaTime*backgroundMaxScrollingSpeed;

        for(int layer =0; layer < backgroundOffsets.length; layer++){
            if(backgroundOffsets[layer]>WORLD_HEIGHT)
            {
                backgroundOffsets[layer] = 0;
            }
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffsets[layer]+WORLD_HEIGHT, WORLD_WIDTH,WORLD_HEIGHT);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        //backgrounds.dispose(); // Lembre-se de descartar a textura para evitar vazamento de memória.
    }
}
