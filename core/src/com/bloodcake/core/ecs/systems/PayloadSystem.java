package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.bloodcake.core.ecs.components.*;
import com.bloodcake.core.ecs.weapons.PayloadFactory;

/**
 * Created by henke on 4/7/2016.
 */
public class PayloadSystem extends IteratingSystem implements Listener<CollisionSystem.Collision> {
    private ComponentMapper<PayloadComponent> pm;
    private ComponentMapper<TransformComponent> tm;
    private Engine engine;
    private PayloadFactory factory;
    private static final Family family = Family.all(PayloadComponent.class, TransformComponent.class).get();

    public PayloadSystem(PayloadFactory factory) {
        super(family);
        this.factory = factory;
        pm = ComponentMapper.getFor(PayloadComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void processEntity(Entity entity, float delta) {
        PayloadComponent payload = pm.get(entity);

        payload.timer += delta;

        if(payload.delay > 0 && payload.timer > payload.delay) {
            // activate payload on next frame if not already activated (if there is no collision)
            if(payload.active)
                activatePayload(entity);

            payload.active = true;
        }
    }

    @Override
    public void receive(Signal<CollisionSystem.Collision> signal, CollisionSystem.Collision collision) {
        if(family.matches(collision.entity)) {
            activatePayload(collision.entity);
        }
    }

    private void activatePayload(Entity entity) {
        TransformComponent transform = tm.get(entity);
        factory.create(transform.position);

        engine.removeEntity(entity);

        Gdx.app.log("PayloadSystem", "loading pay!");
    }
}
