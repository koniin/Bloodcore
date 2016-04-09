package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by henke on 4/5/2016.
 */
public class TransformComponent implements Component {
    public Vector2 position;
    public Vector2 scale;
    public float rotation = 0.0f;

    public TransformComponent(float x, float y, float rotation) {
        position = new Vector2(x, y);
        scale = new Vector2(1, 1);
        this.rotation = rotation;
    }
}
