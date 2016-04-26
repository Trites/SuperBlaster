package com.mygdx.engine.entity.instantiate;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;
import com.mygdx.engine.entity.managers.World;

public interface EntityBlueprint
{
    Entity instantiate(final World world, final Transform transform);
}
