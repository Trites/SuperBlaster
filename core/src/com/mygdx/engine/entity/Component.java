package com.mygdx.engine.entity;


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

    public <T extends Component> T getComponent(Class<T> type) { return entity.getComponent(type); }
}
