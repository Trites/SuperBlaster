package com.mygdx.game.component.spawner;

import com.mygdx.engine.entity.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;
import com.mygdx.engine.entity.instantiate.EntityBlueprint;

public abstract class SpawnOnCollision extends Behaviour
{
    protected EntityBlueprint blueprint;

    protected SpawnOnCollision(final Entity entity, final EntityBlueprint blueprint) {
	super(entity);
	this.blueprint = blueprint;

	getEntity().collisionEvent.subscribe(this::collisionResponse);
    }

    private void collisionResponse(final CollisionComponent other){

	instantiate();
    }

    protected abstract void instantiate();
}
