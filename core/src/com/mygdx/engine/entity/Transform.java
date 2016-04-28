package com.mygdx.engine.entity;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.util.Util;

/**
 * Convinience class containing position, scale and rotation.
 */
public class Transform
{
    private Vector2 position;
    private Vector2 scale;
    private float rotation;

    public Transform(final Vector2 position) {
	this(position, new Vector2(1, 1), 0);
    }

    public Transform(final Vector2 position, final Vector2 scale, final float rotation) {
	this.position = position;
	this.rotation = rotation;
	this.scale = scale;
    }

    public Vector2 getPosition() {
	return position;
    }

    public float getX() { return position.x; }

    public float getY() { return position.y; }

    public void setPosition(final Vector2 position) {
	this.position = position;
    }

    public float getRotation() {
	return rotation;
    }

    public void setRotation(final float rotation) {
	this.rotation = rotation;
    }

    public Vector2 getScale() {
	return scale;
    }

    public float getScaleX() { return scale.x; }

    public float getScaleY() { return scale.y; }

    public void setScale(final Vector2 scale) {
	this.scale = scale;
    }

    public Vector2 getForwardVector(){

	return new Vector2((float)Math.cos(rotation * Util.DEG_TO_RAD), (float)Math.sin(rotation * Util.DEG_TO_RAD)).nor();
    }

    public void lookAt(final Vector2 point){

	this.rotation = (float)(Math.atan2(point.y - getY(), point.x - getX()) * Util.RAD_TO_DEG);
    }

    public void turnTowards(final Vector2 point, final float step){

	Vector2 target = new Vector2(point).sub(position).nor();
	float diffAngle = new Vector2(getForwardVector()).nor().dot(target);

	rotation += (float) (Math.acos(diffAngle) * Util.RAD_TO_DEG * Math.signum(Util.crossScalar(getForwardVector(), target)) *
			     step);
    }
}
