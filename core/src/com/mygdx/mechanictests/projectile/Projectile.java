package com.mygdx.mechanictests.projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;

public class Projectile extends Sprite {

    public Projectile(){
        super((Texture) MechanicTests.manager.get("projectile.png"));
    }

    public void update(float delta){
        int projectileVelocity = 600;
        this.setY(this.getY() + projectileVelocity *delta);
    }

    public boolean isOutOfScreen(){
        return this.getY() > GameScreen.WORLD_HEIGHT;
    }
}
