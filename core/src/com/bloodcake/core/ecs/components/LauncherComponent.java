package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.bloodcake.core.ecs.weapons.Launcher;

/**
 * Created by henke on 4/8/2016.
 */
public class LauncherComponent implements Component {
    public Launcher launcher;
    public float timer;
    public float delay;
    public Vector2 target;
    public int shotsFired;
    public int shotsPerBurst;
}
