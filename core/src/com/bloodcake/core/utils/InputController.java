package com.bloodcake.core.utils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by henke on 3/20/2016.
 */
public class InputController implements InputProcessor {
    public IntMap<Boolean> KeyState;
    private IntIntMap keyMap;

    /*
        int myLeftKey = 100;
        IntIntMap myKeysMap = new IntIntMap();
        myKeysMap.put(Input.Keys.A, myLeftKey);
     */

    public InputController(IntIntMap keyMap) {
        this.keyMap = keyMap;

        /*
        keyMap = new IntMap<Keys>();
        keyMap.put(Input.Keys.A, Keys.LEFT);
        keyMap.put(Input.Keys.D, Keys.RIGHT);
        keyMap.put(Input.Keys.W, Keys.UP);
        keyMap.put(Input.Keys.S, Keys.DOWN);
        keyMap.put(Input.Keys.E, Keys.ATTACK);
        keyMap.put(Input.Keys.SPACE, Keys.ROLL);
        keyMap.put(Input.Keys.LEFT, Keys.LEFT);
        keyMap.put(Input.Keys.RIGHT, Keys.RIGHT);
        keyMap.put(Input.Keys.UP, Keys.UP);
        keyMap.put(Input.Keys.DOWN, Keys.DOWN);
        */

        KeyState = new IntMap<Boolean>();
        IntIntMap.Values values = keyMap.values();
        while(values.hasNext()) {
            KeyState.put(values.next(), false);
        }
        /*
        KeyState.put(Keys.UP, false);
        KeyState.put(Keys.DOWN, false);
        KeyState.put(Keys.LEFT, false);
        KeyState.put(Keys.RIGHT, false);
        KeyState.put(Keys.QUIT, false);
        KeyState.put(Keys.ATTACK, false);
        KeyState.put(Keys.ROLL, false);
        */
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keyMap.containsKey(keycode)) {
            KeyState.put(keyMap.get(keycode, -1), true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keyMap.containsKey(keycode)) {
            KeyState.put(keyMap.get(keycode, -1), false);
        }
        return true;
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
