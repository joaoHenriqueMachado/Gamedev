package com.mygdx.mechanictests.explosion;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ExplosionController {
    private static ConcurrentLinkedQueue<Explosion> aliveExplosion;
    private static ConcurrentLinkedQueue<Explosion> deadExplosion;

    public static void init(){
        aliveExplosion = new ConcurrentLinkedQueue<>();
        deadExplosion = new ConcurrentLinkedQueue<>();
    }

    public static void set(float x, float y, Rectangle boundingBox){
        Explosion e;
        if(!deadExplosion.isEmpty()){
            e = deadExplosion.remove();
            e.setExplosionTimer(0);
            e.setBoundingBox(boundingBox);
        }else{
            e = new Explosion(boundingBox);
        }
        aliveExplosion.add(e);
    }

    public static void draw(SpriteBatch batch, float delta){
        for(Explosion e: aliveExplosion){
            e.update(delta);
            e.draw(batch);

            if(e.isFinished()){
                aliveExplosion.remove(e);
                deadExplosion.add(e);
            }
        }
    }
}
