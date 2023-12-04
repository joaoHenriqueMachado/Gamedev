package com.mygdx.mechanictests.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mechanictests.GameScreen;
import com.mygdx.mechanictests.MechanicTests;
import com.mygdx.mechanictests.projectile.Projectile;
import com.mygdx.mechanictests.projectile.ProjectileController;

public class Enemy extends Sprite {
    private float current;

    public Vector2 initialPosition;
    public Vector2 currentPosition;
    private final Vector2 currentAngle;

    private Bezier<Vector2> path;

    public void setPath(Bezier<Vector2> path) {
        this.path = path;
    }

    private int offsetX;
    private int offsetY;
    public float speed;

    public void setCurrent(float current) {
        this.current = current;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setInitialPosition(float x, float y) {
        this.initialPosition.set(x,y);
    }

    public void setCurrentPosition(Vector2 currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public Enemy(String enemyType, Bezier<Vector2> path) {
        super(MechanicTests.shipTextureRegion[2][0]);
        this.flip(true, true);
        initialPosition = new Vector2();
        currentPosition = new Vector2();
        currentAngle = new Vector2();
        current = 0;
        offsetX = 0;
        offsetY = 0;
        speed = 0.4f;
        this.path = path;
    }

    public void update(float delta) {
        current += speed * delta;

        this.getBoundingRectangle().getPosition(currentPosition);
        path.valueAt(currentPosition, current);
        path.derivativeAt(currentAngle, current);
        this.setX(currentPosition.x + offsetX);
        this.setY(currentPosition.y + offsetY);
        this.setRotation(currentAngle.angleDeg() + 90);
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

    public void increaseSpeed(int score){
        if(score > 1000 && score < 2500){
            speed = 0.45f;
        }else if(score > 2500){
            speed = 0.5f;
        }
    }
}
