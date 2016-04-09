package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/6/2016.
 */
public class MovementSystem extends IteratingSystem {
    private Vector2 tmp = new Vector2();

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float delta) {
        TransformComponent transform = tm.get(entity);
        MovementComponent mov = mm.get(entity);

        /*
        tmp.set(mov.acceleration).scl(delta);
        mov.velocity.add(tmp);
        tmp.set(mov.velocity).scl(delta);
        transform.position.x += tmp.x;
        transform.position.y += tmp.y;
        */

        mov.velocity.x += mov.acceleration.x * delta;
        mov.velocity.y += mov.acceleration.y * delta;
        transform.position.x += mov.velocity.x * delta;
        transform.position.y += mov.velocity.y * delta;
    }
}
