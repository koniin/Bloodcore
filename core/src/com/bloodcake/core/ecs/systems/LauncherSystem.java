package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/8/2016.
 */
public class LauncherSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<LauncherComponent> lm;
    private Engine engine;

    public LauncherSystem() {
        super(Family.all(LauncherComponent.class, TransformComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        lm = ComponentMapper.getFor(LauncherComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        LauncherComponent launcherComponent = lm.get(entity);
        TransformComponent transformComponent = tm.get(entity);

        if(launcherComponent.timer == 0) {
            launcherComponent.launcher.launch(transformComponent.position.cpy(), launcherComponent.target.cpy());
            launcherComponent.shotsFired++;
        }

        launcherComponent.timer += delta;
        if(launcherComponent.timer > launcherComponent.delay) {
            launcherComponent.timer = 0;
        }

        if(launcherComponent.shotsFired >= launcherComponent.shotsPerBurst) {
            engine.removeEntity(entity);
            Gdx.app.log("LauncherSystem", "removing launcher, shotsfired: " + launcherComponent.shotsFired);
        }
    }
}
