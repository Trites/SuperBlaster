package com.mygdx.engine.entity.component.defaultcomponent;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.component.ManagedComponent;
import com.mygdx.engine.entity.managers.ComponentManager;

/**
 * ManagedComponent that deals with velocity and movement.
 */
@SuppressWarnings("unused")
public class RigidBody extends ManagedComponent
{

    private float mass;
    private float linearDampening;
    private float angularDampening;

    private Vector2 velocity;
    private float angularVelocity;

   public RigidBody(final Entity entity, float mass) {
	this(entity, mass, 0.0f, 0.0f);
    }

    public RigidBody(final Entity entity, float mass, float linearDampening, float angularDampening) {
	super(entity);

	this.mass = mass;
	this.linearDampening = linearDampening;
	this. angularDampening = angularDampening;

	velocity = new Vector2(0,0);
	angularVelocity = 0.0f;
    }

    public void update(float deltaTime){

	getTransform().setPosition(getTransform().getPosition().mulAdd(velocity, deltaTime));
	getTransform().setRotation(getTransform().getRotation() + angularVelocity * deltaTime);

	velocity.lerp(new Vector2(0,0), linearDampening);
	MathUtils.lerp(angularVelocity, 0.0f, angularDampening);
    }

    public void addVelocity(final Vector2 deltaV){

	velocity.add(deltaV);
    }

    public void limitVelocity(final float maxVelocity){

	if(velocity.len() > maxVelocity)
	            velocity.sub(new Vector2(velocity).nor().scl(velocity.len() - maxVelocity));
    }

    @Override
    public void register(final ComponentManager world) {

	world.registerComponent(this);
    }

    @Override
     public void deregister(final ComponentManager world) {

 	world.deregisterComponent(this);
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

    public Vector2 getDirection() { return new Vector2(velocity).nor(); }

    public Vector2 getMomentum() { return new Vector2(velocity).scl(mass); }

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
