package com.mygdx.mechanictests.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;

public class Ship extends Sprite {

    ShipInputProcessor shipInputProcessor;
    float velocityY = 0;
    float velocityX = 0;
    float accelerationX = 1000;
    float accelerationY = 1000;
    float stopFriction = 0.9f;

    public Ship() {
        super(MechanicTests.manager.<Texture>get("spaceship.png"));
        shipInputProcessor = new ShipInputProcessor();
        MechanicTests.addInputProcessor(shipInputProcessor);
        this.setX((float)GameScreen.WORLD_WIDTH / 2 - this.getWidth() / 2);
    }

    public void draw(SpriteBatch batch, float delta) {
        super.draw(batch);
        update(delta);
    }

    public void update(float delta) {
        if (shipInputProcessor.left) {
            this.setRegion(MechanicTests.manager.<Texture>get("spaceship_left.png"));
            velocityX -= accelerationX * delta;
        } else if (shipInputProcessor.right) {
            this.setRegion(MechanicTests.manager.<Texture>get("spaceship_right.png"));
            velocityX += accelerationX * delta;
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
        }

        if (!shipInputProcessor.left && !shipInputProcessor.right) {
            velocityX *= stopFriction;
        }

        float updatedYPosition = this.getY() + velocityY * delta;
        if(updatedYPosition > 0 && updatedYPosition < GameScreen.WORLD_HEIGHT - this.getHeight()){
            this.setY(this.getY() + velocityY * delta);
        }else{
            velocityY = 0;
        }


        float updatedXPosition = this.getX() + velocityX * delta;
        if(updatedXPosition > 0 && updatedXPosition < GameScreen.WORLD_WIDTH - this.getWidth()){
            this.setX(this.getX() + velocityX * delta);
        }else{
            velocityX = 0;
        }

    }
}
