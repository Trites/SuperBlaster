package com.mygdx.engine.entity.component.defaultcomponent;

/**
 * CollisionVisitor interface
 */
public interface CollisionVisitor
{
    boolean intersects(CircleCollider other);
    float edgeDistance(CircleCollider other);
}
