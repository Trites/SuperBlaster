package com.mygdx.engine.entity.defaultcomponents;

public interface CollisionElement
{
    boolean intersectVisit(CollisionVisitor other);
}
