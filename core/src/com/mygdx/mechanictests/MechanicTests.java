package com.mygdx.mechanictests;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class MechanicTests extends Game {
	GameScreen gameScreen;
	public static AssetManager manager;
	public static InputMultiplexer multiplexer;
	public static Music defaultMusic;

	public static void addInputProcessor(InputProcessor inputProcessor){
		if (multiplexer == null) multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(inputProcessor);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void create () {
		manager = new AssetManager();
		manager.load("spaceship.png", Texture.class);
		manager.load("new_spaceship.png", Texture.class);
		manager.load("spaceship_left.png", Texture.class);
		manager.load("spaceship_right.png", Texture.class);
		manager.load("projectile.png", Texture.class);
		manager.load("laser_ball.png", Texture.class);
		manager.load("sounds/wavetable.mp3", Music.class);
		manager.load("sounds/laser_gun.mp3", Sound.class);
		//manager.load("sounds/explosion_sound.mp3", Sound.class);

		manager.finishLoading();
		defaultMusic = manager.get("sounds/wavetable.mp3");
		defaultMusic.setLooping(true);
		defaultMusic.setVolume(0.02f);
		defaultMusic.play();

		Gdx.graphics.setWindowedMode(1024, 720);

		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}
