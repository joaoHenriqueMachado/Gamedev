package com.mygdx.mechanictests;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class MainMenuScreen implements Screen {

    private final Stage stage;
    private final Sound buttonHoverSound;
    private SpriteBatch batch;
    private float originalWidth;
    private float originalHeight;
    private Texture backgroundTexture;
    public MainMenuScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        buttonHoverSound = Gdx.audio.newSound(Gdx.files.internal("button_hover.wav"));
        batch = new SpriteBatch();
        Table table = new Table();
        table.setFillParent(true);

        TextureRegionDrawable buttonStartImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("StartButton.png")));
        TextureRegionDrawable buttonQuitImage = new TextureRegionDrawable(new Texture(Gdx.files.internal("QuitButton.png")));
        backgroundTexture = new Texture("BackgroundMenu.png");
        originalWidth = backgroundTexture.getWidth();
        originalHeight = backgroundTexture.getHeight();
        ImageButton startButton = new ImageButton(buttonStartImage);
        ImageButton exitButton = new ImageButton(buttonQuitImage);

        startButton.addListener(new ClickListener() {
            @Override
            public void enter(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                buttonHoverSound.play();
            }

            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("Button", "Start Button click");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void enter(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                Gdx.app.log("Button", "Start Button Entered");
                buttonHoverSound.play();
            }

            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.exit(); // Close the application
            }
        });


        table.center();
        table.add().padTop(0).row();
        table.add(startButton).padTop(100).row();
        table.add(exitButton).padTop(40);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float scaleX = screenWidth / backgroundTexture.getWidth();
        float scaleY = screenHeight / backgroundTexture.getHeight();

        batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);

        batch.end();
        // Draw the stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
    public void dispose() {
        stage.dispose();
        buttonHoverSound.dispose();
    }
}