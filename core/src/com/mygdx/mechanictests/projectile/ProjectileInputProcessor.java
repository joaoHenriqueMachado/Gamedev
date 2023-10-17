package com.mygdx.mechanictests.projectile;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.mechanictests.GameScreen;

public class ProjectileInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        ProjectileController.shot.play();
        ProjectileController.set(GameScreen.ship.getX() + GameScreen.ship.getWidth() / 2 - 16, GameScreen.ship.getY() + GameScreen.ship.getHeight());
        ProjectileController.set(GameScreen.ship.getX() + GameScreen.ship.getWidth() / 4 - 16, GameScreen.ship.getY() + GameScreen.ship.getHeight());
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
