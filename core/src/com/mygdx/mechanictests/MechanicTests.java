package com.mygdx.mechanictests;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MechanicTests extends Game {
	GameScreen gameScreen;
	public static AssetManager manager;
	public static InputMultiplexer multiplexer;
	public static Music defaultMusic;

	public static TextureRegion[][] shipTextureRegion;

	public static void addInputProcessor(InputProcessor inputProcessor){
		if (multiplexer == null) multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(inputProcessor);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void create () {
		manager = new AssetManager();
		manager.load("spaceship.png", Texture.class);
		manager.load("spaceship_green.png", Texture.class);
		manager.load("ships.png", Texture.class);
		manager.load("new_spaceship.png", Texture.class);
		manager.load("spaceship_left.png", Texture.class);
		manager.load("spaceship_right.png", Texture.class);
		manager.load("projectile.png", Texture.class);
		manager.load("laser_sprites/02.png", Texture.class);
		manager.load("laser_sprites/57.png", Texture.class);
		manager.load("laser_ball.png", Texture.class);
		manager.load("sounds/wavetable.mp3", Music.class);
		manager.load("sounds/laser_gun.mp3", Sound.class);
		manager.load("bg_1.png", Texture.class);
		manager.load("bg_2.png", Texture.class);
		manager.load("sounds/explosion_sound.wav", Sound.class);
		manager.load("sounds/laser_shot.wav", Sound.class);

		manager.finishLoading();
		defaultMusic = manager.get("sounds/wavetable.mp3");
		defaultMusic.setLooping(true);
		defaultMusic.setVolume(0.05f);
		defaultMusic.play();

		Gdx.graphics.setWindowedMode(1600, 900);

		gameScreen = new GameScreen();
		setScreen(gameScreen);

		Texture tex = manager.get("ships.png");
		shipTextureRegion = TextureRegion.split(tex, 256, 256);
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
