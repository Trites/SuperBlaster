package com.mygdx.engine.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Class containing usefull utility methods and constants.
 */
public final class Util
{

    /**
     * PI in degrees.
     */
    public static final float PI_DEG = 180.0f;
    /**
     * Constant that converts radians to degrees.
     */
    public static final float RAD_TO_DEG = PI_DEG / (float)Math.PI;
    /**
     * Constant that converts degrees to radians.
     */
    public static final float DEG_TO_RAD = (float)Math.PI / PI_DEG;

    private Util() {}

    public static float clamp(final float value, final float min, final float max){

	return Math.max(min, Math.min(max, value));
    }

    public static Color shiftColor(final Color color, final float variation){

	Vector3 direction = new Vector3((float)Math.random(), (float)Math.random(), (float)Math.random()).nor();
	return shiftColor(color, direction, variation);
    }

    public static Color shiftColor(final Color color, final Vector3 direction, final float variation){

	//noinspection MagicNumber
	float colorDistance = (float)Math.random()*variation - variation / 2.0f;
	direction.scl(colorDistance);

	Color newColor = new Color(color);
	newColor.r = clamp (newColor.r + direction.x, 0.0f, 1.0f);
	newColor.g = clamp (newColor.g + direction.y, 0.0f, 1.0f);
	newColor.b = clamp (newColor.b + direction.z, 0.0f, 1.0f);

	return newColor;
    }

    public static Color randomColor(final float saturation){

	Vector3 direction = new Vector3((float)Math.random(), (float)Math.random(), (float)Math.random()).nor();
	direction.scl(saturation);


	return new Color(direction.x, direction.y, direction.z, 1.0f);
    }

    public static Vector2 getBounceVelocity(final Vector2 posA, final Vector2 posB, final Vector2 velA, final Vector2 velB,
    						final float massA, final float massB){

	Vector2 line = new Vector2(posB).sub(posA).nor();

	float a1 = new Vector2(velA).dot(line);
	float a2 = new Vector2(velB).dot(line);

	//noinspection MagicNumber
	float p = (2.0f * (a2 - a1)) / (massA + massB);



	Vector2 dv = new Vector2(line).scl(p*massB);

	return new Vector2(velA).add(dv);
    }

    public static float crossScalar(final Vector2 a, final Vector2 b){

	return a.x*b.y - a.y*b.x;
    }

    public static float random(final float min, final float max){

	return (float)Math.random()*(max - min) + min;
    }

}
