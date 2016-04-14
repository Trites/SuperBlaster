package com.mygdx.engine.entity.managers;

import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.defaultcomponents.RenderComponent;
import com.mygdx.engine.entity.defaultcomponents.RigidBody;

public interface ComponentManager
{
    void registerComponent(final RigidBody component);
    void registerComponent(final CollisionComponent component);
    void registerComponent(final RenderComponent component);
    void deregisterComponent(final RigidBody component);
    void deregisterComponent(final CollisionComponent component);
    void deregisterComponent(final RenderComponent component);
}
