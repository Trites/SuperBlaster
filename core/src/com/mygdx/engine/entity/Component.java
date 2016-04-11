package com.mygdx.engine.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Component
{

    private Entity entity;

    protected Component(final Entity entity) {

        this.entity = entity;
    }

    public void start(){}

    public Entity getEntity() {
        return entity;
    }

    public Transform getTransform() { return entity.getTransform(); }
}
