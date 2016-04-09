package com.bloodcake.core.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.bloodcake.core.ecs.components.*;
import com.bloodcake.core.ecs.weapons.Weapon;

/**
 * Created by henke on 4/6/2016.
 */
public class WeaponSystem extends IteratingSystem {
    private ComponentMapper<WeaponComponent> wm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<InputComponent> im;

    public WeaponSystem() {
        super(Family.all(InputComponent.class, WeaponComponent.class, TransformComponent.class).get());

        wm = ComponentMapper.getFor(WeaponComponent.class);
        im = ComponentMapper.getFor(InputComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent input = im.get(entity);
        WeaponComponent weaponComponent = wm.get(entity);

        updateWeaponTimers(weaponComponent);

        if(input.requestFire) {
            Weapon selectedWeapon = weaponComponent.weapons.get(input.selectedWeapon);
            if(selectedWeapon.timeLeft == selectedWeapon.shootDelay) {
                fireWeapon(entity, input, selectedWeapon);
            }
        }
    }

    private void updateWeaponTimers(WeaponComponent weaponComponent) {
        long millis = TimeUtils.millis();
        for(Weapon weapon : weaponComponent.weapons.values()) {
            if (millis - weapon.lastShot < weapon.shootDelay) {
                // Remove to queue fire when ready
                //inputContext.fireRequested = false;
                weapon.timeLeft = millis - weapon.lastShot;
            } else {
                weapon.timeLeft = weapon.shootDelay;
            }
        }
    }

    private void fireWeapon(Entity entity, InputComponent input, Weapon selectedWeapon) {
        Gdx.app.log("weapon system", "firing: " + selectedWeapon.name + " at " + input.fireTarget);
        selectedWeapon.lastShot = TimeUtils.millis();
        TransformComponent transform = tm.get(entity);
        selectedWeapon.fire(transform.position, input.fireTarget);
    }
}
