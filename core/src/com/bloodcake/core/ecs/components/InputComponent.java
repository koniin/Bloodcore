package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by henke on 4/5/2016.
 */
public class InputComponent implements Component {
    public int selectedWeapon;
    public boolean requestFire;
    public Vector2 fireTarget;
}
