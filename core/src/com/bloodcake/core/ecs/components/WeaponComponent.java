package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.IntMap;
import com.bloodcake.core.ecs.weapons.Weapon;

/**
 * Created by henke on 4/6/2016.
 */
public class WeaponComponent implements Component {
    public IntMap<Weapon> weapons;

    public WeaponComponent(IntMap<Weapon> weapons) {
        this.weapons = weapons;
    }
}
