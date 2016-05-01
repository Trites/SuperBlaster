package com.mygdx.engine.entity.instantiate;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.managers.CollisionManager;
import com.mygdx.engine.entity.managers.RenderManager;
import com.mygdx.engine.entity.managers.World;

/**
 * Interface for instantiate method signature.
 */

//Used as a method signature that should return value.
public interface EntityBlueprint
{
    Entity instantiate(final World<CollisionManager, RenderManager> world, final Transform transform);
}
