package com.bloodcake.core.ecs.weapons;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by henke on 4/8/2016.
 */
public class Launcher {
    private ProjectileFactory projectileFactory;

    public Launcher(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }

    public void launch(Vector2 position, Vector2 target) {
        projectileFactory.create(position, target);
    }
}
