package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/7/2016.
 */
public class HealthRenderSystem extends IteratingSystem {
    private ComponentMapper<HealthComponent> hm;
    private ComponentMapper<TransformComponent> tm;
    private SpriteBatch batch;
    private Viewport viewport;
    private OrthographicCamera gameCam;
    private BitmapFont font;
    private Vector3 screenCoords;

    public HealthRenderSystem(SpriteBatch batch, Viewport viewport, OrthographicCamera gameCam) {
        super(Family.all(HealthComponent.class, TransformComponent.class).get());
        this.batch = batch;
        this.viewport = viewport;
        this.gameCam = gameCam;
        font = new BitmapFont();
        screenCoords = new Vector3();
        //font.getData().setScale(0.05f, 0.05f);


        tm = ComponentMapper.getFor(TransformComponent.class);
        hm = ComponentMapper.getFor(HealthComponent.class);
    }

    @Override
    protected void processEntity(com.badlogic.ashley.core.Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        HealthComponent health = hm.get(entity);

        screenCoords.set(transform.position.x, transform.position.y, 0);
        screenCoords = gameCam.project(screenCoords);

        batch.begin();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        font.draw(batch, "" + health.health, screenCoords.x, screenCoords.y);
        batch.end();
    }
}
