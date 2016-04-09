package com.bloodcake.core.ecs.weapons;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/8/2016.
 */
public class LauncherFactory extends ProjectileFactory {
    private final Engine engine;
    private final ProjectileFactory projectileFactory;
    private float delay = 0.3f;
    private int shotsPerBurst = 3;

    public LauncherFactory(Engine engine, ProjectileFactory projectileFactory) {
        super(engine);
        this.engine = engine;
        this.projectileFactory = projectileFactory;
    }

    public LauncherFactory(Engine engine, ProjectileFactory projectileFactory, float delay, int shotsPerBurst) {
        this(engine, projectileFactory);
        this.delay = delay;
        this.shotsPerBurst = shotsPerBurst;
    }

    @Override
    public void create(Vector2 position, Vector2 target) {
        Entity entity = new Entity();
        entity.add(new TransformComponent(position.x, position.y, 0));
        LauncherComponent launcherComponent = new LauncherComponent();
        launcherComponent.delay = delay;
        launcherComponent.launcher = new Launcher(projectileFactory);
        launcherComponent.shotsPerBurst = shotsPerBurst;
        launcherComponent.target = target.cpy();
        entity.add(launcherComponent);
        engine.addEntity(entity);
    }
}
