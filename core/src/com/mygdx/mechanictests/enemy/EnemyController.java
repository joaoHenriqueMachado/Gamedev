package com.mygdx.mechanictests.enemy;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;
import com.mygdx.mechanictests.paths.Paths;

import java.util.Random;
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

    private static Bezier<Vector2> path;
    private static Random random;

    private static int offsetX;
    private static int offsetY;

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
        int id = random.nextInt(5);
        random = new Random();
        switch(id){
            case 0:
                path = Paths.path1;
                offsetY = 0;
                offsetX = random.nextInt(-200, 200);
                break;
            case 1:
                path = Paths.path2;
                offsetY = 0;
                offsetX = random.nextInt(-200, 200);
                break;
            case 2:
                path = Paths.xLine;
                offsetY = random.nextInt(-200, 200);
                offsetX = 0;
                break;
            case 3:
                path = Paths.yLine;
                offsetY = 0;
                offsetX = random.nextInt(-200, 200);
                break;
            case 4:
                path = Paths.sine;
                offsetY = 0;
                offsetX = random.nextInt(-200, 200);
                break;
        }
    }

    public static void spawnEnemies(float delta){
        waveCounter += delta;
        if(waveCounter >= spawnTime){
            if(waveEnemyCount > 0){
                EnemyController.set((float)GameScreen.WORLD_WIDTH/2, GameScreen.WORLD_HEIGHT, path);
                waveEnemyCount--;
            }
            waveCounter -= spawnTime;
        }
    }

    public static void init(){
        aliveEnemies = new ConcurrentLinkedQueue<>();
        deadEnemies = new ConcurrentLinkedQueue<>();
        random = new Random();
        path = Paths.sine;
        offsetX = 0;
        offsetY = 0;
        waveCounter = 0;
        spawnTime = 0.15f;
        explosionSound = MechanicTests.manager.get("sounds/explosion_sound.wav");

    }

    public static void set(float x, float y, Bezier<Vector2> path){
        Enemy a;
        if(!deadEnemies.isEmpty()){
            a = deadEnemies.remove();
        }else{
            a = new Enemy(path);
        }
        a.setX(x);
        a.setY(y);
        a.setCurrent(0);
        a.setInitialPosition(x,y);
        a.setPath(path);
        a.setOffsetX(offsetX);
        a.setOffsetY(offsetY);
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
        e.increaseSpeed(GameScreen.score);
        aliveEnemies.remove(e);
        deadEnemies.add(e);
        long id = explosionSound.play();
        explosionSound.setVolume(id, 0.1f);
    }
}
