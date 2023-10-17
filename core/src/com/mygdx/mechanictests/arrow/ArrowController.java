package com.mygdx.mechanictests.arrow;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.MechanicTests;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class ArrowController {
    private static ConcurrentLinkedQueue<Arrow> aliveArrows;
    private static ConcurrentLinkedQueue<Arrow> deadArrows;
    public static Sound bowLoading;
    private static Sound bowRelease;
    public static ConcurrentLinkedQueue<Arrow> getAliveArrows() {
        return aliveArrows;
    }

    public static void init(){
        aliveArrows = new ConcurrentLinkedQueue<Arrow>();
        deadArrows = new ConcurrentLinkedQueue<Arrow>();
        ArrowInputProcessor arrowInputProcessor = new ArrowInputProcessor();
        MechanicTests.addInputProcessor(arrowInputProcessor);
        bowLoading = MechanicTests.manager.get("sounds/bow-loading.mp3");
        bowRelease = MechanicTests.manager.get("sounds/bow-release.mp3");
    }


    public static void set(float x, float y){
        Arrow a;
        if (!deadArrows.isEmpty()){
            a = deadArrows.remove();
        }else{
            a = new Arrow();
        }
        aliveArrows.add(a);
        long id = bowRelease.play();
        bowRelease.setPan(id, -1, 0.6f);
        bowRelease.setPitch(id, 2);
        a.setX(x);
        a.setY(y);
    }

    public static void draw(SpriteBatch batch, float delta){
        for (Arrow a : aliveArrows){
            a.draw(batch);
            a.update(delta);

            if (a.isOutOfScreen()){
                aliveArrows.remove(a);
                deadArrows.add(a);
            }
        }

    }

}
