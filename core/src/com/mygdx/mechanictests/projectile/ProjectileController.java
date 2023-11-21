package com.mygdx.mechanictests.projectile;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.MechanicTests;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class ProjectileController {
    private static ConcurrentLinkedQueue<Projectile> aliveProjectiles;
    private static ConcurrentLinkedQueue<Projectile> deadProjectiles;
    private static ConcurrentLinkedQueue<Projectile> secondaryAliveProjectiles;
    private static ConcurrentLinkedQueue<Projectile> secondaryDeadProjectiles;
    public static ProjectileInputProcessor projectileInputProcessor;
    public static Sound shot;
    public static ConcurrentLinkedQueue<Projectile> getAliveProjectiles() {
        return aliveProjectiles;
    }

    public static ConcurrentLinkedQueue<Projectile> getDeadProjectiles() {
        return deadProjectiles;
    }

    public static ConcurrentLinkedQueue<Projectile> getSecondaryAliveProjectiles() {
        return secondaryAliveProjectiles;
    }

    public static ConcurrentLinkedQueue<Projectile> getSecondaryDeadProjectiles() {
        return secondaryDeadProjectiles;
    }

    public static void init(){
        aliveProjectiles = new ConcurrentLinkedQueue<>();
        deadProjectiles = new ConcurrentLinkedQueue<>();
        secondaryAliveProjectiles = new ConcurrentLinkedQueue<>();
        secondaryDeadProjectiles = new ConcurrentLinkedQueue<>();
        projectileInputProcessor = new ProjectileInputProcessor();
        MechanicTests.addInputProcessor(projectileInputProcessor);
        shot = MechanicTests.manager.get("sounds/laser_gun.mp3");
    }

    public static void set(float x, float y){
        Projectile a;
        if(projectileInputProcessor.projectileType){
            if (!secondaryDeadProjectiles.isEmpty()){
                a = secondaryDeadProjectiles.remove();
            }else{
                a = new Projectile();
            }
            secondaryAliveProjectiles.add(a);
            long id = shot.play();
            shot.setPan(id, -1, 0.3f);
            shot.setPitch(id, 2);
        }else{
            if (!deadProjectiles.isEmpty()){
                a = deadProjectiles.remove();
                a.setHit(false);
            }else{
                a = new Projectile("projectile.png");
            }
            aliveProjectiles.add(a);
            long id = shot.play();
            shot.setPan(id, -1, 0.3f);
            shot.setPitch(id, 2);
        }
        a.setX(x);
        a.setY(y);
    }

    public static void draw(SpriteBatch batch, float delta){
        for (Projectile a : aliveProjectiles){
            a.draw(batch);
            a.update(delta);

            if (a.isOutOfScreen() || a.hit){
                aliveProjectiles.remove(a);
                deadProjectiles.add(a);
            }
        }
        for (Projectile a : secondaryAliveProjectiles){
            a.draw(batch);
            a.update(delta);

            if (a.isOutOfScreen()){
                secondaryAliveProjectiles.remove(a);
                secondaryDeadProjectiles.add(a);
            }
        }
    }
}
