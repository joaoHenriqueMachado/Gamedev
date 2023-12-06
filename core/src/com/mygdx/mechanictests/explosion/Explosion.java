package com.mygdx.mechanictests.explosion;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.mechanictests.MechanicTests;

public class Explosion extends Animation<TextureRegion>{
    private float explosionTimer;
    private Rectangle boundingBox;

    public void setExplosionTimer(float explosionTimer) {
        this.explosionTimer = explosionTimer;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Explosion(Rectangle boundingBox) {
        super(0.7f/16, MechanicTests.explosionTextureRegion);
        this.boundingBox = boundingBox;
        explosionTimer = 0;
    }

    public void update(float deltaTime) {
        explosionTimer += deltaTime;
    }

    public void draw (SpriteBatch batch) {
        batch.draw(this.getKeyFrame(explosionTimer),
                boundingBox.x,
                boundingBox.y,
                boundingBox.width,
                boundingBox.height);
    }

    public boolean isFinished() {
        return this.isAnimationFinished(explosionTimer);
    }

}
