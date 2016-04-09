package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by henke on 4/7/2016.
 */
public class PayloadComponent implements Component {
    public boolean active;
    public float timer;
    public float delay;

    public PayloadComponent() {
        active = true;
    }

    public PayloadComponent(boolean activate) {
        active = activate;
    }
}
