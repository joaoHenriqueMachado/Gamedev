package com.mygdx.mechanictests.ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.MechanicTests;

public class Ship extends Sprite {

    ShipInputProcessor shipInputProcessor;
    static int maneuverSpeed = 16;
    public Ship(){
        super(MechanicTests.manager.<Texture>get("spaceship.png"));
        shipInputProcessor = new ShipInputProcessor();
        MechanicTests.addInputProcessor(shipInputProcessor);
    }

    public void draw(SpriteBatch batch, float delta) {
        super.draw(batch);
        update(delta);
    }

    public void update(float delta){
        if(shipInputProcessor.left){
            this.setRegion(MechanicTests.manager.<Texture>get("ship_left.png"));
            this.setX(this.getX() - maneuverSpeed*delta);
        }
        if(shipInputProcessor.right){
            this.setRegion(MechanicTests.manager.<Texture>get("ship_right.png"));
            this.setX(this.getX() + maneuverSpeed*delta);
        }
    }
}
