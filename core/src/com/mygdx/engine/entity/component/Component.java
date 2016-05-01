package com.mygdx.engine.entity.component;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.Destroyable;
import com.mygdx.engine.entity.Startable;

import java.util.List;

/**
 * Base component. Is derived into ManagedComponnt and Behaviour.
 */
public class Component implements Destroyable, Startable
{

    private boolean startActive;

    private Entity entity;
    private boolean active;
    private boolean alive;

    protected Component(final Entity entity) {
        this(entity, true);
    }

    protected Component(final Entity entity, final boolean startActive) {

        this.entity = entity;
        this.active = false;
        this.alive = true;
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

    protected <T extends Component>  void requireComponent(Class<T> type){ entity.requireComponent(type);}

    protected List<Entity> findEntity(final String tag){ return entity.findEntity(tag); }

    @Override
    public void destroy(){
        active = false;
        alive = false;
        entity.removeComponent(this);
    }

    @Override
    public void dispose() {

        active = false;
        alive = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public boolean isAlive() {
        return alive;
    }
}
