package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/6/2016.
 */
public class ExplosionSystem extends IteratingSystem {
    private ComponentMapper<ExplosionComponent> em;
    private Engine engine;

    public ExplosionSystem() {
        super(Family.all(ExplosionComponent.class).get());
        em = ComponentMapper.getFor(ExplosionComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        ExplosionComponent explosion = em.get(entity);

        explosion.timer += deltaTime;

        if(explosion.timer > explosion.lifeTime) {
            engine.removeEntity(entity);
            Gdx.app.log("Explosion system", "removing explosion");
        }
    }
}
