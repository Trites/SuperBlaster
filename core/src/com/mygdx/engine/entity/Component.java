package com.mygdx.engine.entity;

import com.mygdx.engine.entity.managers.Destroyable;
import com.mygdx.engine.entity.managers.Startable;

import java.util.List;

public abstract class Component implements Destroyable, Startable
{

    private boolean startActive;

    private Entity entity;
    private boolean active;

    protected Component(final Entity entity) {
        this(entity, true);
    }

    protected Component(final Entity entity, final boolean startActive) {

        this.entity = entity;
        this.active = false;
        this.startActive = startActive;
    }

    public void start(){
        this.active = startActive;
    }

    public Entity getEntity() {
        return entity;
    }

    public Transform getTransform() { return entity.getTransform(); }

    public <T extends Component> T getComponent(Class<T> type) { return entity.getComponent(type); }

    protected List<Entity> findEntity(final String tag){ return entity.findEntity(tag); }

    @Override
    public void destroy(){ active = false; }

    @Override
    public void destroyImmediate() {

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}
