package com.mygdx.mechanictests.ship;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class ShipInputProcessor implements InputProcessor {
    boolean left, right, up, down;
    boolean projectileType;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            left = true;
        }
        if (keycode == Input.Keys.D) {
            right = true;
        }
        if (keycode == Input.Keys.W) {
            up = true;
        }
        if (keycode == Input.Keys.S) {
            down = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {
            left = false;
        }
        if (keycode == Input.Keys.D) {
            right = false;
        }
        if (keycode == Input.Keys.W) {
            up = false;
        }
        if (keycode == Input.Keys.S) {
            down = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
