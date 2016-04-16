package com.mygdx.engine.entity;

import com.badlogic.gdx.math.Vector2;

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

	return new Vector2((float)Math.cos(rotation*(Math.PI/180)), (float)Math.sin(rotation*(Math.PI/180)));
    }
}
