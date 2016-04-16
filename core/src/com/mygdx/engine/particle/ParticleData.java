package com.mygdx.engine.particle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class ParticleData
{
    private Vector2 velocity;
    private Color color;
    private float lifeTime;

    public ParticleData(final Vector2 velocity, final Color color, final float lifeTime) {
	this.velocity = velocity;
	this.color = color;
	this.lifeTime = lifeTime;
    }

    public Vector2 getVelocity() {
	return velocity;
    }

    public void setVelocity(final Vector2 velocity) {
	this.velocity = velocity;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(final Color color) {
	this.color = color;
    }

    public float getLifeTime() {
	return lifeTime;
    }

    public void setLifeTime(final float lifeTime) {
	this.lifeTime = lifeTime;
    }

    public void updateLifeTime(final float deltaTime){

	lifeTime -= deltaTime;
    }
}
