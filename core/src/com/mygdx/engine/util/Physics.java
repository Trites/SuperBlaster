package com.mygdx.engine.util;

import com.badlogic.gdx.math.Vector2;

public final class Physics
{


    private Physics() {}

    public static Vector2 rayCast(final Vector2 rayStart, final Vector2 direction, float distance, final Vector2 center,
				  final float radius){

	return rayCast(rayStart, new Vector2(rayStart).mulAdd(direction, distance), center, radius);

    }

    public static Vector2 rayCast(final Vector2 rayStart, final Vector2 rayEnd, final Vector2 center,
						 final float radius){

	Vector2 rayDelta = new Vector2(rayEnd).sub(rayStart);
	Vector2 unitDelta = new Vector2(rayDelta).nor();

	float dist = (center.x - rayStart.x) * unitDelta.y - (center.y - rayStart.y) * unitDelta.x;

	if(Math.abs(dist) > radius){

	    return null;
	}

	Vector2 unitPerp = new Vector2(-unitDelta.y, unitDelta.x);
	Vector2 closest = new Vector2(unitPerp).scl(dist).add(center);
	float dline = (float)Math.sqrt(radius*radius - dist*dist);

	Vector2 hit = new Vector2(unitDelta).scl(-dline).add(closest);

	return hit;
    }
}
