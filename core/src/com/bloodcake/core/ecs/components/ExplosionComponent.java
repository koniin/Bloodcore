package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by henke on 4/7/2016.
 */
public class ExplosionComponent implements Component {
    public float lifeTime;
    public float timer = 0;

    public ExplosionComponent(float lifeTime) {
        this.lifeTime = lifeTime;
    }
}
