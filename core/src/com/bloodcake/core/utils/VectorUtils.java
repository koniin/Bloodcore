package com.bloodcake.core.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by henke on 4/6/2016.
 */
public class VectorUtils {
    public static Vector2 getDirectionVector(Vector2 position, Vector2 target) {
        return target.sub(position).nor();
    }

    public static float angleBetween(float x1, float y1, float x2, float y2) {
        return MathUtils.atan2(y2 - y1, x2 - x1);
    }

    public static float angleBetween(Vector2 first, Vector2 second) {
        return angleBetween(first.x, first.y, second.x, second.y);
    }
}
