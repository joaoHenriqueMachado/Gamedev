package com.mygdx.mechanictests.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;
import com.mygdx.mechanictests.paths.Paths;
import com.mygdx.mechanictests.projectile.Projectile;
import com.mygdx.mechanictests.projectile.ProjectileController;

public class Enemy extends Sprite {
    private final float speed = 0.25f;
    private float current;
    private final Vector2 currentPosition;
    private final Vector2 currentAngle;

    private Bezier<Vector2> path;

    public void setCurrent(float current) {
        this.current = current;
    }

    public Enemy(String enemyType, Bezier<Vector2> path) {
        super((Texture) MechanicTests.manager.get("new_spaceship.png"));
        this.flip(true, true);
        currentPosition = new Vector2();
        currentAngle = new Vector2();
        current = 0;
        this.path = path;
    }

    public void update(float delta) {
        current += speed * delta;

        this.getBoundingRectangle().getPosition(currentPosition);
        path.valueAt(currentPosition, current);
        path.derivativeAt(currentAngle, current);
        this.setX(currentPosition.x);
        this.setY(currentPosition.y);
        this.setRotation(currentAngle.angleDeg() + 90);
        //System.out.println(currentPosition.x + "    " + currentPosition.y);
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
