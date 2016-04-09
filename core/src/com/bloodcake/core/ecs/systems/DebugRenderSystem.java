package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/6/2016.
 */
public class DebugRenderSystem extends EntitySystem {
    private final ShapeRenderer shapeRenderer;
    private ImmutableArray<Entity> entities;

    private ComponentMapper<BoundsComponent> bm = ComponentMapper.getFor(BoundsComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    private SpriteBatch batch;
    private OrthographicCamera gameCamera;

    public DebugRenderSystem(SpriteBatch batch, OrthographicCamera gameCamera) {
        this.batch = batch;
        this.gameCamera = gameCamera;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, BoundsComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {
        TransformComponent transform;
        BoundsComponent bounds;

        for (int i = 0; i < entities.size(); ++i) {
            Entity damageEntity = entities.get(i);

            transform = tm.get(damageEntity);
            bounds = bm.get(damageEntity);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(gameCamera.combined);

            shapeRenderer.rect(bounds.bounds.x, bounds.bounds.y, bounds.bounds.width, bounds.bounds.height);

            shapeRenderer.end();
        }
    }
}