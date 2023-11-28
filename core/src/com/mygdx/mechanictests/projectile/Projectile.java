package com.mygdx.mechanictests.projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;

public class Projectile extends Sprite {
    int projectileVelocity = 600;
    public boolean hit;

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public Projectile(){
        super((Texture) MechanicTests.manager.get("projectile.png"));
    }

    public Projectile(String texture){
        super((Texture) MechanicTests.manager.get(texture));
    }

    public void update(float delta){
        this.setY(this.getY() + this.projectileVelocity * delta);
    }

    public boolean isOutOfScreen(){
        return this.getY() > GameScreen.WORLD_HEIGHT;
    }
}
