package com.mygdx.mechanictests.arrow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mechanictests.MechanicTests;

public class Arrow extends Sprite {

    public Arrow(){
        super((Texture) MechanicTests.manager.get("arrow.png"));
    }

    public void update(float delta){
        this.setX(this.getX() + 600*delta);
    }

    public boolean isOutOfScreen(){
        return this.getX() > Gdx.graphics.getWidth();
    }

}
