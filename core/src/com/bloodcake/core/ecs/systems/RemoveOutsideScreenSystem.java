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
public class RemoveOutsideScreenSystem extends IteratingSystem {
    private float gameWidth;
    private float gameHeight;
    private ComponentMapper<BoundsComponent> bm = ComponentMapper.getFor(BoundsComponent.class);
    private Engine engine;

    public RemoveOutsideScreenSystem(float gameWidth, float gameHeight) {
        super(Family.all(TransformComponent.class, BoundsComponent.class).get());
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    public void resize(float gameWidth, float gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent boundsComponent = bm.get(entity);

        if(boundsComponent.bounds.x < -boundsComponent.bounds.width
                || boundsComponent.bounds.y < -boundsComponent.bounds.height
                || boundsComponent.bounds.x > gameWidth + boundsComponent.bounds.width
                || boundsComponent.bounds.y > gameHeight + boundsComponent.bounds.height) {
            Gdx.app.log("RemoveOutsideSystem", "Removed entity");
            engine.removeEntity(entity);
        }
    }
}
