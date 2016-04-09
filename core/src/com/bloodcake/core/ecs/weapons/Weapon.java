package com.bloodcake.core.ecs.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by henke on 4/6/2016.
 */
public class Weapon {
    public String name;
    public int uid;
    private ProjectileFactory projectileFactory;
    public long shootDelay; // In ms
    public long lastShot; // In ms
    public long timeLeft;

    public Weapon(String name, int uid, int shootDelay, ProjectileFactory projectileFactory) {
        this.name = name;
        this.uid = uid;
        this.shootDelay = shootDelay;
        this.lastShot = 0;
        this.timeLeft = shootDelay;
        this.projectileFactory = projectileFactory;
    }

    public void fire(Vector2 position, Vector2 target) {
        Gdx.app.log("Weapon", "fire at x: " + target.x + ", y:" + target.y);
        projectileFactory.create(position, target);
    }
}
