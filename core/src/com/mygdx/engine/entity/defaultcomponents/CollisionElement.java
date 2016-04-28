package com.mygdx.engine.entity.defaultcomponents;

/**
 * Collision element interface. Works together with CollisionVisitor.
 */
public interface CollisionElement
{
    boolean intersectVisit(CollisionVisitor other);
}
