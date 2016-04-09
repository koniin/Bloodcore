package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by henke on 4/5/2016.
 */
public class VisualComponent implements Component {
    public TextureRegion region;
    public float width;
    public float height;
    public float originX;
    public float originY;

    public VisualComponent (TextureRegion region, float width, float height) {
        this.region = region;
        this.width = width;
        this.height = height;
        this.originX = width * 0.5f;
        this.originY = height * 0.5f;
    }

    public void setOrigin(float x, float y) {
        setOriginX(x);
        setOriginY(y);
    }

    public void setOriginX(float x) {
        this.originX = x;
    }

    public void setOriginY(float y) {
        this.originY = y;
    }
}
