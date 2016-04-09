package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.bloodcake.core.ecs.components.AnimationComponent;
import com.bloodcake.core.ecs.components.VisualComponent;

/**
 * Created by henke on 4/7/2016.
 */
public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<VisualComponent> tm;
    private ComponentMapper<AnimationComponent> am;
    /*private ComponentMapper<StateComponent> sm;*/

    public AnimationSystem() {
        super(Family.all(VisualComponent.class,
                AnimationComponent.class
                /*, StateComponent.class */).get());

        tm = ComponentMapper.getFor(VisualComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
        //sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        VisualComponent tex = tm.get(entity);
        AnimationComponent anim = am.get(entity);
        //StateComponent state = sm.get(entity);

        Animation animation = anim.animations.get(anim.animation);

        if (animation != null) {
            tex.region = animation.getKeyFrame(anim.time);
        }

        anim.time += deltaTime;
    }
}
