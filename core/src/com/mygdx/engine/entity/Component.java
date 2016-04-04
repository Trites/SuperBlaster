package com.mygdx.engine.entity;

public abstract class Component
{
    private Entity entity;

    protected Component(final Entity entity) {
        this.entity = entity;
    }

    public abstract void update(final float deltaTime);

    public Entity getEntity() {
        return entity;
    }

    public Transform getTransform() { return entity.getTransform(); }
}
