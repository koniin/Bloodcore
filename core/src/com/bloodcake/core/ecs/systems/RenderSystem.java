package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/5/2016.
 */
public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private ImmutableArray<Entity> particles;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<VisualComponent> vm = ComponentMapper.getFor(VisualComponent.class);
    private ComponentMapper<ParticleComponent> pm = ComponentMapper.getFor(ParticleComponent.class);

    public RenderSystem (SpriteBatch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, VisualComponent.class).get());
        particles = engine.getEntitiesFor(Family.all(TransformComponent.class, ParticleComponent.class).get());
    }

    @Override
    public void removedFromEngine (Engine engine) {

    }

    @Override
    public void update (float deltaTime) {
        TransformComponent transform;
        VisualComponent visual;
        ParticleComponent particle;

        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);


        for (int i = 0; i < entities.size(); ++i) {
            Entity e = entities.get(i);

            transform = tm.get(e);
            visual = vm.get(e);

            batch.draw(visual.region,
                    transform.position.x - visual.originX, transform.position.y - visual.originY,
                    //transform.position.x, transform.position.y,
                    visual.originX, visual.originY,
                    visual.width, visual.height,
                    transform.scale.x, transform.scale.y,
                    MathUtils.radiansToDegrees * transform.rotation);

            //batch.draw(visual.region, transform.position.x, transform.position.y, 0,0, visual.width, visual.height, transform.scale.x, transform.scale.y, transform.rotation);
        }

        for(int j = 0; j < particles.size(); j++) {
            Entity e = particles.get(j);
            particle = pm.get(e);
            particle.effect.draw(batch);
        }

        batch.end();
    }
}
