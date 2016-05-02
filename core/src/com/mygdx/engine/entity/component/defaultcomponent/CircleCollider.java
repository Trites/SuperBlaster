package com.mygdx.engine.entity.component.defaultcomponent;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

/**
 * Derived from CollisionComponent. This class specifies the collision behaviour of a circle.
 */
public class CircleCollider extends CollisionComponent
{
    private float radius;

    public CircleCollider(final Entity entity, float radius, final byte collisionLayer) {

	super(entity, collisionLayer);

	this.radius = radius;
    }


    /**
     * Invokes the intersects method of the other component with 'this' as value.
     * @param other The other component of the collision check.
     * @return True if components intersects.
     */
    @Override
    public boolean intersectVisit(final CollisionVisitor other) {

	//Check if the collider collides with itself for some reasion
	//Note: I don't want to check if they are "equal"
	if(other == this)
	    return false;

	return other.intersects(this);
    }

    /**
     * Defines the collision check between two circles.
     * @param other The other circle.
     * @return True if circles intersects.
     */
    @Override
    public boolean intersects(final CircleCollider other) {

	if(!this.isActive() || !other.isActive())
	    return false;

	return edgeDistance(other) <= 0;
    }

    /**
     * Calculates the smallest edge distance for two circles.
     * @param other The other circle.
     * @return The distance from the edge of this circle to the edge of the other circle.
     */
    @Override
    public float edgeDistance(final CircleCollider other){

	return Vector2.dst(this.getTransform().getX(),
			   this.getTransform().getY(),
			   other.getTransform().getX(),
			   other.getTransform().getY()) - (this.radius + other.radius);
    }

    /**
     *
     * @return The radius of this circle.
     */
    public float getRadius() {
	return radius;
    }

    /**
     * Draws the circle using ShapeRenderer.
     * @param renderer The ShapeRenderer to be used for rendering.
     */
    @Override
    public void render(final ShapeRenderer renderer) {

	renderer.circle(getTransform().getPosition().x, getTransform().getPosition().y, radius);
    }
}
