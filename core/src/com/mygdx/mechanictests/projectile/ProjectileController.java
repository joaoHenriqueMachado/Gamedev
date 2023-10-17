package com.mygdx.mechanictests.projectile;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.MechanicTests;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class ProjectileController {
    private static ConcurrentLinkedQueue<Projectile> aliveProjectiles;
    private static ConcurrentLinkedQueue<Projectile> deadProjectiles;
    public static Sound shot;
    public static ConcurrentLinkedQueue<Projectile> getAliveProjectiles() {
        return aliveProjectiles;
    }

    public static void init(){
        aliveProjectiles = new ConcurrentLinkedQueue<Projectile>();
        deadProjectiles = new ConcurrentLinkedQueue<Projectile>();
        ProjectileInputProcessor projectileInputProcessor = new ProjectileInputProcessor();
        MechanicTests.addInputProcessor(projectileInputProcessor);
        shot = MechanicTests.manager.get("sounds/laser_gun.mp3");
    }


    public static void set(float x, float y){
        Projectile a;
        if (!deadProjectiles.isEmpty()){
            a = deadProjectiles.remove();
        }else{
            a = new Projectile();
        }
        aliveProjectiles.add(a);
        long id = shot.play();
        shot.setPan(id, -1, 0.3f);
        shot.setPitch(id, 2);
        a.setX(x);
        a.setY(y);
    }

    public static void draw(SpriteBatch batch, float delta){
        for (Projectile a : aliveProjectiles){
            a.draw(batch);
            a.update(delta);

            if (a.isOutOfScreen()){
                aliveProjectiles.remove(a);
                deadProjectiles.add(a);
            }
        }

    }
}
