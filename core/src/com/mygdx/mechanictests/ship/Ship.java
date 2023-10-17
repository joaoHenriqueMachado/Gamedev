package com.mygdx.mechanictests.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.MechanicTests;

public class Ship extends Sprite {

    ShipInputProcessor shipInputProcessor;
    static int maneuverSpeed = 200;
    float velocityY = 0;
    float velocityX = 0;
    float accelerationX = 1000;
    float accelerationY = 400;
    float friction = 1f;
    float stopFriction = 0.9f;

    public Ship() {
        super(MechanicTests.manager.<Texture>get("spaceship.png"));
        shipInputProcessor = new ShipInputProcessor();
        MechanicTests.addInputProcessor(shipInputProcessor);
        this.setX((float)Gdx.graphics.getWidth() / 2 - this.getWidth() / 2);
    }

    public void draw(SpriteBatch batch, float delta) {
        super.draw(batch);
        update(delta);
    }

    public void update(float delta) {
        if (shipInputProcessor.left) {
            this.setRegion(MechanicTests.manager.<Texture>get("spaceship_left.png"));
            this.setX(this.getX() - maneuverSpeed * delta);
        } else if (shipInputProcessor.right) {
            this.setRegion(MechanicTests.manager.<Texture>get("spaceship_right.png"));
            this.setX(this.getX() + maneuverSpeed * delta);
        } else {
            this.setRegion(MechanicTests.manager.<Texture>get("spaceship.png"));
        }

        if (shipInputProcessor.up) {
            velocityY += accelerationY * delta;
        }
        if (shipInputProcessor.down) {
            velocityY -= accelerationY * delta;
        }
        if (!shipInputProcessor.up && !shipInputProcessor.down) {
            velocityY *= stopFriction;
        } else {
            velocityY *= friction;
        }

        velocityY *= friction;

        this.setY(this.getY() + velocityY * delta);


        if (shipInputProcessor.right) {
            velocityX += accelerationX * delta;
        }
        if (shipInputProcessor.left) {
            velocityX -= accelerationX * delta;
        }

        if (!shipInputProcessor.left && !shipInputProcessor.right) {
            velocityX *= stopFriction;
        } else {
            velocityX *= friction;
        }

        this.setX(this.getX() + velocityX * delta);
    }
}
