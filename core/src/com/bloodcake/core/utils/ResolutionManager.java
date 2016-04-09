package com.bloodcake.core.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by henke on 3/10/2016.
 */
public class ResolutionManager implements InputProcessor {
    private final Array<Vector2> resolutions;
    private int resolution;

    public ResolutionManager() {
        resolution = 0;
        resolutions = new Array<Vector2>();
        resolutions.add(new Vector2(800, 480));
        resolutions.add(new Vector2(960, 640)); // Iphone 4
        resolutions.add(new Vector2(1136, 640)); // Iphone 5
        resolutions.add(new Vector2(1280, 720)); // HD 720P
        resolutions.add(new Vector2(1334, 750)); // Iphone 6
        resolutions.add(new Vector2(1920, 1080)); // Full HD 1080P
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.F7) {
            resolution++;
            if(resolution == resolutions.size)
                resolution = 0;
            Gdx.graphics.setWindowedMode((int)resolutions.get(resolution).x, (int)resolutions.get(resolution).y);
            Gdx.app.log("resolutionManager", "Changed resolution to: " + resolutions.get(resolution));
            return true;
        }
        if(keycode == Input.Keys.F8) {
            resolution--;
            if(resolution < 0)
                resolution = resolutions.size - 1;
            Gdx.graphics.setWindowedMode((int)resolutions.get(resolution).x, (int)resolutions.get(resolution).y);
            Gdx.app.log("resolutionManager", "Changed resolution to: " + resolutions.get(resolution));
            return true;
        }
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
    public boolean scrolled(int amount) {
        return false;
    }
}
