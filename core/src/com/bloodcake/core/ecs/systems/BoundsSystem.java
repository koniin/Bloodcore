package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.bloodcake.core.ecs.components.BoundsComponent;
import com.bloodcake.core.ecs.components.TransformComponent;

/**
 * Created by henke on 4/6/2016.
 */
public class BoundsSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<BoundsComponent> bm;

    public BoundsSystem() {
        super(Family.all(BoundsComponent.class, TransformComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        bm = ComponentMapper.getFor(BoundsComponent.class);
    }

    @Override
    protected void processEntity(com.badlogic.ashley.core.Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        BoundsComponent bounds = bm.get(entity);

        if(bounds.fixedBounds)
            return;

        bounds.bounds.x = transform.position.x - bounds.bounds.width * 0.5f;
        bounds.bounds.y = transform.position.y - bounds.bounds.height * 0.5f;
    }
}
