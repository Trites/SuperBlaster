package com.mygdx.engine.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Util
{
    public static float clamp(final float value, final float min, final float max){

	return Math.max(min, Math.min(max, value));
    }

    public static Color shiftColor(final Color color, final float variation){

	Vector3 direction = new Vector3((float)Math.random(), (float)Math.random(), (float)Math.random()).nor();
	return shiftColor(color, direction, variation);
    }

    public static Color shiftColor(final Color color, final Vector3 direction, final float variation){

	float colorDistance = (float)Math.random()*variation - variation/2f;
	direction.scl(colorDistance);

	Color newColor = new Color(color);
	newColor.r = clamp (newColor.r + direction.x, 0f, 1f);
	newColor.g = clamp (newColor.g + direction.y, 0f, 1f);
	newColor.b = clamp (newColor.b + direction.z, 0f, 1f);

	return newColor;
    }

    public static float vectorAngle(final Vector2 a, final Vector2 b){

	//return a.nor().dot(b.nor());

	return (float)(Math.atan2(b.y - a.y, b.x - a.x));// * 180/Math.PI);
    }

    public static Vector2 getBounceVelocity(final Vector2 posA, final Vector2 posB, final Vector2 velA, final Vector2 velB,
    						final float massA, final float massB){

	Vector2 line = new Vector2(posB).sub(posA).nor();

	float a1 = new Vector2(velA).dot(line);
	float a2 = new Vector2(velB).dot(line);

	float p = (2f * (a2-a1))/(massA + massB);



	Vector2 dv = new Vector2(line).scl(p*massB);

	return new Vector2(velA).add(dv);
    }
}
