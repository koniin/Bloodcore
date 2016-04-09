package com.bloodcake.core.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by henke on 4/7/2016.
 */
public class AnimationComponent implements Component {
    public IntMap<Animation> animations = new IntMap<Animation>();
    public float time = 0.0f;
    public int animation = 0;
}
