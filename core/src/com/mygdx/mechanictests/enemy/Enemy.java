package com.mygdx.mechanictests.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;
import com.mygdx.mechanictests.projectile.Projectile;
import com.mygdx.mechanictests.projectile.ProjectileController;

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
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfScreen(){
        boolean outsideYAxis = this.getY() < -this.getHeight();
        boolean outsideXAxis = this.getX() > GameScreen.WORLD_WIDTH || this.getX() < 0;
        if(outsideXAxis || outsideYAxis){
            System.out.println("Removed enemy at " + this.getBoundingRectangle());
        }
        return outsideYAxis || outsideXAxis;
    }
}
