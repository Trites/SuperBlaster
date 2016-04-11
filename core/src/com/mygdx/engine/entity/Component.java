package com.mygdx.engine.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Component
{
    private List<Class<? extends Component>> requieredComponents;

    private Entity entity;

    protected Component(final Entity entity) {

        this.entity = entity;
        requieredComponents = new ArrayList<>();
    }

    public void start(){}

    public Entity getEntity() {
        return entity;
    }

    public Transform getTransform() { return entity.getTransform(); }

    protected void requireComponent(final Class<? extends Component> component){

        requieredComponents.add(component);
    }

    public final List<Class<? extends Component>> getRequieredComponents(){

        return requieredComponents;
    }
}
