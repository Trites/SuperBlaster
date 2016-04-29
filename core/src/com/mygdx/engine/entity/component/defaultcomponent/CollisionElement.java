package com.mygdx.engine.entity.component.defaultcomponent;

/**
 * Collision element interface. Works together with CollisionVisitor.
 */
public interface CollisionElement
{
    boolean intersectVisit(CollisionVisitor other);
}
