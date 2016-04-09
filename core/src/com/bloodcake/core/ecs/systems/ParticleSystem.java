package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/7/2016.
 */
public class ParticleSystem extends IteratingSystem {
    private ComponentMapper<ParticleComponent> pm;
    private ComponentMapper<TransformComponent> tm;
    private Engine engine;

    public ParticleSystem() {
        super(Family.all(ParticleComponent.class, TransformComponent.class).get());
        pm = ComponentMapper.getFor(ParticleComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }
/*

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }
*/

    @Override
    public void processEntity(Entity entity, float delta) {
        ParticleComponent particle = pm.get(entity);
        TransformComponent transform = tm.get(entity);

        particle.effect.update(delta);

        // TODO: should probably set position of all emitters
        particle.effect.getEmitters().first().setPosition(transform.position.x, transform.position.y);
        if(!particle.started) {
            particle.effect.start();
            particle.started = true;
        }

        if (particle.autoReset && particle.effect.isComplete())
            particle.effect.reset();
    }
}