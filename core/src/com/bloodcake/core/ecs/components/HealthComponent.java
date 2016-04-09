package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by henke on 4/7/2016.
 */
public class HealthComponent implements Component {
    public int health;

    public HealthComponent(int health) {
        this.health = health;
    }
}
