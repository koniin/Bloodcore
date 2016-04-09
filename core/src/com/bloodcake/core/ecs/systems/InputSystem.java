package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.bloodcake.core.ecs.components.*;

/**
 * Created by henke on 4/6/2016.
 */
public class InputSystem extends IteratingSystem {
    //private WeaponContext weaponContext;
    private ComponentMapper<InputComponent> im;

    public InputSystem() {
        super(Family.all(InputComponent.class, PlayerComponent.class).get());
        //this.weaponContext = weaponContext;

        im = ComponentMapper.getFor(InputComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent i = im.get(entity);
        /*
        i.selectedWeapon = weaponContext.selectedWeapon;
        i.fireTarget = weaponContext.fireTarget;
        i.requestFire = weaponContext.fireRequested;

        weaponContext.fireRequested = false;
        */
    }
}
