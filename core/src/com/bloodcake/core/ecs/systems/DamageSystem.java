package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/7/2016.
 */
public class DamageSystem extends IteratingSystem implements Listener<CollisionSystem.Collision> {
    private static final Family family = Family.all(DamageComponent.class).get();
    private static final Family healthFamily = Family.all(HealthComponent.class).get();
    private ComponentMapper<DamageComponent> dm = ComponentMapper.getFor(DamageComponent.class);
    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
    public DamageSystem() {
        super(family);
    }

    @Override
    public void receive(Signal<CollisionSystem.Collision> signal, CollisionSystem.Collision collision) {
        Gdx.app.log("DamageSystem", "handle it!");
        if(collision.entity == null)
            Gdx.app.log("DamageSystem", "null wtf?");

        if(family.matches(collision.entity) && healthFamily.matches(collision.collidedWith)) {
            DamageComponent damageComponent = dm.get(collision.entity);
            HealthComponent healthComponent = hm.get(collision.collidedWith);

            healthComponent.health -= damageComponent.damage;
        }
    }

    @Override
    protected void processEntity(Entity entity, float delta) {

    }
}
