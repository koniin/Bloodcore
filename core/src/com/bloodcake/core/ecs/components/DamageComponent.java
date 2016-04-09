package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by henke on 4/6/2016.
 */
public class DamageComponent implements Component {
    public int damage;

    public DamageComponent(int damage) {
        this.damage = damage;
    }
}
