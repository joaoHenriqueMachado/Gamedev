package com.mygdx.mechanictests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mechanictests.enemy.EnemyController;
import com.mygdx.mechanictests.explosion.ExplosionController;
import com.mygdx.mechanictests.paths.Paths;
import com.mygdx.mechanictests.projectile.ProjectileController;
import com.mygdx.mechanictests.ship.Ship;

import java.util.Locale;

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

    //Heads-Up Display
    BitmapFont font;
    float hudVerticalMargin, hudLeftX, hudRightX, hudCentreX, hudRow1Y, hudRow2Y, hudSectionWidth;

    public static int counter;
    public GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        backgrounds = new Texture[4];

        backgrounds[0] = MechanicTests.manager.get("bg_2.png");
        backgrounds[1] = new Texture("Starscape01.png");
        backgrounds[2] = new Texture("Starscape01.png");
        backgrounds[3] = new Texture("Starscape03.png");

        backgroundMaxScrollingSpeed = (float)(WORLD_HEIGHT) / 16;
        batch = new SpriteBatch();
        ship = new Ship();
        Paths.init();
        ProjectileController.init();
        EnemyController.init();
        EnemyController.generateWave(8);
        ExplosionController.init();
        counter = 0;
        prepareHUD();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        renderBackground(delta);
        ship.draw(batch, delta);
        ProjectileController.draw(batch, delta);
        EnemyController.spawnEnemies(delta);
        EnemyController.draw(batch, delta);
        ExplosionController.draw(batch, delta);
        updateAndRenderHUD();
        batch.end();
        //Gdx.graphics.setTitle("MechanicTests | Score: " + score + " | Health: " + ship.getHealth());
    }
    private void renderBackground(float deltaTime){
        backgroundOffsets[0] = 0;
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

    private void prepareHUD() {
        //Create a BitmapFont from our font file
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("SpaceMono-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 942;
        fontParameter.borderWidth = 6.5f;
        fontParameter.color = new Color(1, 1, 1, 0.3f);
        fontParameter.borderColor = new Color(0, 0, 0, 0.3f);

        font = fontGenerator.generateFont(fontParameter);

        //scale the font to fit world
        font.getData().setScale(0.08f);

        //calculate hud margins, etc.
        hudVerticalMargin = font.getCapHeight() / 2;
        hudLeftX = hudVerticalMargin;
        hudRightX = WORLD_WIDTH * 2 / 3 - hudLeftX;
        hudCentreX = WORLD_WIDTH / 3;
        hudRow1Y = WORLD_HEIGHT - hudVerticalMargin;
        hudRow2Y = hudRow1Y - hudVerticalMargin - font.getCapHeight();
        hudSectionWidth = WORLD_WIDTH / 3;
    }
    private void updateAndRenderHUD() {
        //render top row labels
        font.draw(batch, "Score", hudLeftX, hudRow1Y, hudSectionWidth, Align.left, false);

        font.draw(batch, "HealtH", hudRightX, hudRow1Y, hudSectionWidth, Align.right, false);
        //render second row values
        font.draw(batch, String.format(Locale.getDefault(), "%06d", score), hudLeftX, hudRow2Y, hudSectionWidth, Align.left, false);
        //font.draw(batch, String.format(Locale.getDefault(), "%02d", playerShip.shield), hudCentreX, hudRow2Y, hudSectionWidth, Align.center, false);
        font.draw(batch, String.format(Locale.getDefault(), "%02d", ship.getHealth()), hudRightX, hudRow2Y, hudSectionWidth, Align.right, false);
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
    }
}
