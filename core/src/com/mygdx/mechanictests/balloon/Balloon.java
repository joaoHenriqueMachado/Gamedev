package com.mygdx.mechanictests.balloon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mechanictests.MechanicTests;
import com.mygdx.mechanictests.arrow.Arrow;
import com.mygdx.mechanictests.arrow.ArrowController;

public class Balloon extends Sprite {

    private int speed;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Balloon(String texture){
        super((Texture) MechanicTests.manager.get(texture));
    }
    public void update(float delta){
        this.setY(this.getY() + speed*delta);
    }

    public boolean wasHit(){
        int arrowTipX = 0;
        int arrowTipY = 0;
        for(Arrow a : ArrowController.getAliveArrows()){
            arrowTipX = (int) (a.getX() + a.getWidth());
            arrowTipY = (int) (a.getY() + a.getHeight() / 2);
            if(arrowTipX >= this.getX() && arrowTipX <= this.getX() + this.getWidth() && arrowTipY >= this.getY() && arrowTipY <= this.getY() + this.getHeight())
              return true;
        }
        return false;
    }

    public boolean isOutOfScreen(){
        return this.getY() > Gdx.graphics.getHeight();
    }
}
