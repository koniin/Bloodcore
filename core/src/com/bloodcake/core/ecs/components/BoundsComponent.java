package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by henke on 4/6/2016.
 */
public class BoundsComponent implements Component {
    public Rectangle bounds;
    public boolean fixedBounds = false;

    public BoundsComponent(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public BoundsComponent(float width, float height) {
        bounds = new Rectangle(0, 0, width, height);
    }
}
