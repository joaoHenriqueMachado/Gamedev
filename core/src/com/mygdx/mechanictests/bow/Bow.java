package com.mygdx.mechanictests.bow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.MechanicTests;

public class Bow extends Sprite {
    BowInputProcessor bowInputProcessor;

    public Bow(){
        super(MechanicTests.manager.<Texture>get("bow.png"));
        bowInputProcessor = new BowInputProcessor();
        MechanicTests.addInputProcessor(bowInputProcessor);
    }

    public void draw(SpriteBatch batch, float delta){
        super.draw(batch);
        update(delta);
    }

    public void update(float delta){
        if (bowInputProcessor.up){
            this.setY(this.getY() + 200*delta);
        }
        if (bowInputProcessor.down){
            this.setY(this.getY() - 200*delta);
        }
    }
}
