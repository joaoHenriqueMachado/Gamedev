package com.mygdx.mechanictests.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;
import com.mygdx.mechanictests.projectile.Projectile;
import com.mygdx.mechanictests.projectile.ProjectileController;
import com.mygdx.mechanictests.projectile.ProjectileInputProcessor;

public class Enemy extends Sprite {
    int speed = -500;
    public Enemy(String enemyType) {
        super((Texture) MechanicTests.manager.get("new_spaceship.png"));
        this.flip(true, true);
    }

    public void update(float delta) {
        this.setY(this.getY() + speed * delta);
    }

    public boolean detectHit(){
        for(Projectile p: ProjectileController.getAliveProjectiles()){
            if(this.getBoundingRectangle().overlaps(p.getBoundingRectangle())){
                p.setHit(true);
                return true;
            }
        }
        for(Projectile p: ProjectileController.getSecondaryAliveProjectiles()){
            if(this.getBoundingRectangle().overlaps(p.getBoundingRectangle())){
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfScreen(){
        boolean outsideYAxis = this.getY() < -this.getHeight();
        boolean outsideXAxis = this.getX() > GameScreen.WORLD_WIDTH || this.getX() < 0;
        return outsideYAxis || outsideXAxis;
    }
}
