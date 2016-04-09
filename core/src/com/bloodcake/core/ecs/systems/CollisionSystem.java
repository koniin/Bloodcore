package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/6/2016.
 */
public class CollisionSystem extends EntitySystem {
    private ImmutableArray<Entity> damageEntities;
    private ImmutableArray<Entity> targets;

    private ComponentMapper<BoundsComponent> bm = ComponentMapper.getFor(BoundsComponent.class);
    private ComponentMapper<PayloadComponent> pm = ComponentMapper.getFor(PayloadComponent.class);

    private Signal<Collision> signal = new Signal<Collision>();

    public void addListener(Listener<Collision> listener) {
        signal.add(listener);
    }

    @Override
    public void addedToEngine(Engine engine) {
        damageEntities = engine.getEntitiesFor(Family.all(BoundsComponent.class, DamageComponent.class, PayloadComponent.class).get());
        targets = engine.getEntitiesFor(Family.all(BoundsComponent.class, TargetComponent.class, HealthComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {
        BoundsComponent damageBounds;

        for (int i = 0; i < damageEntities.size(); i++) {
            Entity damageEntity = damageEntities.get(i);

            damageBounds = bm.get(damageEntity);

            for (int j = 0; j < targets.size(); j++) {
                Entity target = targets.get(j);
                BoundsComponent targetBounds = bm.get(target);

                if(targetBounds.bounds.overlaps(damageBounds.bounds)) {
                    // TODO: Get Collision from pool?

                    PayloadComponent payload = pm.get(damageEntity);
                    if(!payload.active)
                        continue;

                    Collision collision = new Collision();
                    collision.entity = damageEntity;
                    collision.collidedWith = target;
                    signal.dispatch(collision);
                }
            }
        }
    }

    class Collision {
        public Entity entity;
        Entity collidedWith;
    }
}