package com.mygdx.game.entities.component.spawner;

import com.mygdx.engine.entity.component.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.component.defaultcomponent.CollisionComponent;
import com.mygdx.engine.entity.instantiate.EntityBlueprint;

/**
 * Base behaviour that is meant to instantiate one or several Entities as a collision response.
 */
public abstract class SpawnOnCollision extends Behaviour
{
    protected EntityBlueprint blueprint;

    protected SpawnOnCollision(final Entity entity, final EntityBlueprint blueprint) {
	super(entity);
	this.blueprint = blueprint;

	getEntity().collisionEvent.subscribe(this::collisionResponse);
    }

    @SuppressWarnings("UnusedParameters") private void collisionResponse(final CollisionComponent other){

	instantiate();
    }

    protected abstract void instantiate();
}
