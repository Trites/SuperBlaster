package com.mygdx.engine.entity;

import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;

public abstract class Component
{
    private Entity entity;

    protected Component(final Entity entity) {
        this.entity = entity;
    }

    public abstract void update(final float deltaTime);

    public void collisionResponse(CollisionComponent other){}

    public Entity getEntity() {
        return entity;
    }

    public Transform getTransform() { return entity.getTransform(); }
}
