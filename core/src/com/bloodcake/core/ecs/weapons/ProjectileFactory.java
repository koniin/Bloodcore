package com.bloodcake.core.ecs.weapons;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by henke on 4/6/2016.
 */
public abstract class ProjectileFactory {
    protected Engine engine;

    public ProjectileFactory(Engine engine) {
        this.engine = engine;
    }

    public abstract void create(Vector2 position, Vector2 direction);
}
