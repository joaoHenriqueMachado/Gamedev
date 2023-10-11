package com.mygdx.mechanictests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.mechanictests.arrow.ArrowController;
import com.mygdx.mechanictests.balloon.BalloonController;
import com.mygdx.mechanictests.bow.Bow;

import java.util.Random;

public class MechanicTests extends ApplicationAdapter {
	SpriteBatch batch;
	public static Bow bow;
	public static AssetManager manager;
	public static InputMultiplexer multiplexer;
	public static int balloonTimer;
	public static Random random;
	public static long score;
	public static Music defaultMusic;

	public static void addInputProcessor(InputProcessor inputProcessor){
		if (multiplexer == null) multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(inputProcessor);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();

		manager = new AssetManager();
		manager.load("bow.png", Texture.class);
		manager.load("arrow.png", Texture.class);
		manager.load("balloon_blue.png", Texture.class);
		manager.load("balloon_red.png", Texture.class);
		manager.load("sounds/balloon_pop.mp3", Sound.class);
		manager.load("sounds/bow-loading.mp3", Sound.class);
		manager.load("sounds/bow-release.mp3", Sound.class);
		manager.load("sounds/music.mp3", Music.class);
		manager.finishLoading();
		bow = new Bow();
		ArrowController.init();
		BalloonController.init();
		balloonTimer = 0;
		score = 0;
		random = new Random();
		defaultMusic = manager.get("sounds/music.mp3");
		defaultMusic.play();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.graphics.setTitle("Score: " + score);
		batch.begin();
		bow.draw(batch, Gdx.graphics.getDeltaTime());
		ArrowController.draw(batch, Gdx.graphics.getDeltaTime());
		BalloonController.draw(batch, Gdx.graphics.getDeltaTime());
		batch.end();
		if(balloonTimer >= 45) {
			balloonTimer = 0;
			BalloonController.set(400 + random.nextInt(50), 0);
		}
		balloonTimer++;
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
