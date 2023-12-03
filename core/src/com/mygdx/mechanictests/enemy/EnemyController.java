package com.mygdx.mechanictests.enemy;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;
import com.mygdx.mechanictests.paths.Paths;

import java.util.concurrent.ConcurrentLinkedQueue;

public class EnemyController {
    private static ConcurrentLinkedQueue<Enemy> aliveEnemies;
    private static ConcurrentLinkedQueue<Enemy> deadEnemies;

    private static Sound explosionSound;

    private static float waveCounter;
    private static int waveEnemyCount;

    private static int initialWaveEnemyCount;
    private static int enemiesAlive;

    private static float spawnTime;

    public static void killEnemy(){
        if(enemiesAlive > 1){
            enemiesAlive--;
        }else{
            reGenerateWave();
        }
    }

    public static void generateWave(int enemyCount){
        waveEnemyCount = enemyCount;
        enemiesAlive = enemyCount;
        initialWaveEnemyCount = enemyCount;
    }

    public static void reGenerateWave(){
        waveEnemyCount = initialWaveEnemyCount;
        enemiesAlive = initialWaveEnemyCount;
    }

    public static void spawnEnemies(float delta){
        waveCounter += delta;
        if(waveCounter >= spawnTime){
            if(waveEnemyCount > 0){
                EnemyController.set((float)GameScreen.WORLD_WIDTH/2, GameScreen.WORLD_HEIGHT, Paths.sine);
                waveEnemyCount--;
            }
            waveCounter -= spawnTime;
        }
    }

    public static void init(){
        aliveEnemies = new ConcurrentLinkedQueue<>();
        deadEnemies = new ConcurrentLinkedQueue<>();
        waveCounter = 0;
        spawnTime = 0.15f;
        explosionSound = MechanicTests.manager.get("sounds/explosion_sound.wav");

    }

    public static void set(float x, float y, Bezier<Vector2> path){
        Enemy a;
        if(!deadEnemies.isEmpty()){
            a = deadEnemies.remove();
        }else{
            a = new Enemy("", path);
        }
        a.setX(x);
        a.setY(y);
        a.setCurrent(0);
        a.setInitialPosition(x,y);
        aliveEnemies.add(a);
    }

    public static void draw(SpriteBatch batch, float delta){
        for(Enemy e: aliveEnemies){
            e.update(delta);
            e.draw(batch);

            if(e.detectHit()){
                enemyHit(e);
                killEnemy();
            }else if(e.isOutOfScreen()){
                e.setCurrentPosition(e.initialPosition);
                e.setCurrent(0);
            }
        }
    }

    private static void enemyHit(Enemy e){
        GameScreen.score += 10;
        aliveEnemies.remove(e);
        deadEnemies.add(e);
        long id = explosionSound.play();
        explosionSound.setVolume(id, 0.1f);
    }
}
