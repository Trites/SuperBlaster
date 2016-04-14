package com.mygdx.engine.entity;


import com.mygdx.engine.entity.managers.ComponentManager;
import com.mygdx.engine.entity.managers.World;

import java.util.List;

public abstract class Component
{

    private Entity entity;
    private boolean active;

    protected Component(final Entity entity) {

        this.entity = entity;
        this.active = false;
    }

    public void start(){

        active = true;
    }

    public Entity getEntity() {
        return entity;
    }

    public Transform getTransform() { return entity.getTransform(); }

    public <T extends Component> T getComponent(Class<T> type) { return entity.getComponent(type); }

    protected List<Entity> findEntity(final String tag){ return entity.findEntity(tag); }

    public abstract void destroy();

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}
