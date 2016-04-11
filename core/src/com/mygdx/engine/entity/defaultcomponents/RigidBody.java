package com.mygdx.engine.entity.defaultcomponents;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Component;
import com.mygdx.engine.entity.Entity;

public class RigidBody extends Component
{
    private float mass;
    private float linearDampening;
    private float angularDampening;

    private Vector2 velocity;
    private float angularVelocity;

    public RigidBody(final Entity entity, float mass) {
	this(entity, mass, 0f, 0f);
    }

    public RigidBody(final Entity entity, float mass, float linearDampening, float angularDampening) {
	super(entity);

	this.mass = mass;

	velocity = new Vector2(0,0);
	angularVelocity = 0f;
    }

    public void update(float deltaTime){

	getTransform().setPosition(getTransform().getPosition().mulAdd(velocity, deltaTime));
	getTransform().setRotation(getTransform().getRotation() + angularVelocity * deltaTime);

	velocity.lerp(new Vector2(0,0), linearDampening);
	MathUtils.lerp(angularVelocity, 0f, angularDampening);
    }

    public void applyForce(final Vector2 force){

	addVelocity(force.scl(1/mass));
    }

    public void addVelocity(final Vector2 deltaV){

	velocity.add(deltaV);
    }

    public float getMass() {
	return mass;
    }

    public void setMass(final float mass) {
	this.mass = mass;
    }

    public float getLinearDampening() {
	return linearDampening;
    }

    public void setLinearDampening(final float linearDampening) {
	this.linearDampening = linearDampening;
    }

    public float getAngularDampening() {
	return angularDampening;
    }

    public void setAngularDampening(final float angularDampening) {
	this.angularDampening = angularDampening;
    }

    public Vector2 getVelocity() {
	return velocity;
    }

    public void setVelocity(final Vector2 velocity) {
	this.velocity = velocity;
    }

    public float getAngularVelocity() {
	return angularVelocity;
    }

    public void setAngularVelocity(final float angularVelocity) {
	this.angularVelocity = angularVelocity;
    }
}