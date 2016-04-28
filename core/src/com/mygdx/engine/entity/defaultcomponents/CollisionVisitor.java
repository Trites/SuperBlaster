package com.mygdx.engine.entity.defaultcomponents;

public interface CollisionVisitor
{
    boolean intersects(CircleCollider other);
    float edgeDistance(CircleCollider other);
}
