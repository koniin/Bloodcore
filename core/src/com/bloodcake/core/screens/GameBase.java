package com.bloodcake.core.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

/**
 * Created by henke on 4/9/2016.
 */
public interface GameBase {
    float virtualWidth = 40;
    AssetManager assetManager = null;
    void setScreen(Screen screen);
}
