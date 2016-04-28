package com.mygdx.engine.entity.defaultcomponents;

/**
 * CollisionVisitor interface
 */
public interface CollisionVisitor
{
    boolean intersects(CircleCollider other);
    float edgeDistance(CircleCollider other);
}
