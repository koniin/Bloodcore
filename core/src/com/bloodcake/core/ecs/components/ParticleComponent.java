package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

/**
 * Created by henke on 4/7/2016.
 */
public class ParticleComponent implements Component {
    public ParticleEffect effect;
    public boolean started;
    public boolean autoReset;

    public ParticleComponent(ParticleEffect effect) {
        this.effect = effect;
        started = false;
        autoReset = false;
    }
}
