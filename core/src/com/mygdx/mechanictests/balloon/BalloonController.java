package com.mygdx.mechanictests.balloon;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.MechanicTests;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BalloonController {
    private static ConcurrentLinkedQueue<Balloon> aliveBalloons;
    private static ConcurrentLinkedQueue<Balloon> deadBalloons;

    private static ConcurrentLinkedQueue<Balloon> aliveBlueBalloons;
    private static ConcurrentLinkedQueue<Balloon> deadBlueBalloons;
    private static int balloonCounter;
    private static Sound popSound;
    public static void init(){
        aliveBalloons = new ConcurrentLinkedQueue<Balloon>();
        deadBalloons = new ConcurrentLinkedQueue<Balloon>();
        aliveBlueBalloons = new ConcurrentLinkedQueue<Balloon>();
        deadBlueBalloons = new ConcurrentLinkedQueue<Balloon>();
        balloonCounter = 0;
        popSound = MechanicTests.manager.get("sounds/balloon_pop.mp3");
    }

    public static void set(float x, float y){
        Balloon a;
        if(balloonCounter % 3 == 2){
            if(!deadBlueBalloons.isEmpty())
                a = deadBlueBalloons.remove();
            else{
                a = new Balloon("balloon_blue.png");
                a.setSpeed(300);
            }
            aliveBlueBalloons.add(a);
        }else{
            if(!deadBalloons.isEmpty())
                a = deadBalloons.remove();
            else{
                a = new Balloon("balloon_red.png");
                a.setSpeed(200);
            }
            aliveBalloons.add(a);
        }
        balloonCounter++;
        a.setX(x);
        a.setY(y - a.getHeight());
    }

    public static void draw(SpriteBatch batch, float delta){
        for(Balloon b : aliveBlueBalloons){
            b.draw(batch);
            b.update(delta);

            if(b.wasHit()){
                MechanicTests.score += 10;
                aliveBlueBalloons.remove(b);
                deadBlueBalloons.add(b);
                popSound.play();
            }
            else if(b.isOutOfScreen()){
                aliveBlueBalloons.remove(b);
                deadBlueBalloons.add(b);
            }
        }
        for(Balloon b : aliveBalloons){
            b.draw(batch);
            b.update(delta);

            if(b.wasHit()){
                MechanicTests.score += 5;
                aliveBalloons.remove(b);
                deadBalloons.add(b);
                popSound.play();
            }
            else if(b.isOutOfScreen()){
                aliveBalloons.remove(b);
                deadBalloons.add(b);
            }
        }
    }
}
