package com.mygdx.mechanictests.enemy;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.GameScreen;

import java.util.concurrent.ConcurrentLinkedQueue;

public class EnemyController {
    private static ConcurrentLinkedQueue<Enemy> aliveEnemies;
    private static ConcurrentLinkedQueue<Enemy> deadEnemies;

    //private static Sound explosionSound;

    public static void init(){
        aliveEnemies = new ConcurrentLinkedQueue<>();
        deadEnemies = new ConcurrentLinkedQueue<>();
        //explosionSound = MechanicTests.manager.get("sounds/explosion_sound.mp3");
    }

    public static void set(float x, float y){
        Enemy a;
        if(!deadEnemies.isEmpty()){
            a = deadEnemies.remove();
        }else{
            a = new Enemy("");
        }
        aliveEnemies.add(a);
        a.setX(x);
        a.setY(y + a.getHeight());
    }

    public static void draw(SpriteBatch batch, float delta){
        for(Enemy e: aliveEnemies){
            e.update(delta);
            e.draw(batch);

            if(e.detectHit()){
                enemyHit(e);
            }else if(e.isOutOfScreen()){
                aliveEnemies.remove(e);
                deadEnemies.add(e);
            }
        }
    }

    private static void enemyHit(Enemy e){
        GameScreen.score += 10;
        aliveEnemies.remove(e);
        deadEnemies.add(e);
        //explosionSound.play();
    }
}
