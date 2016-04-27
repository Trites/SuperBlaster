package com.mygdx.game.component;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.defaultcomponents.CollisionComponent;

public class KillOnCollision extends OnCollision
{

    public KillOnCollision(final Entity entity, final boolean startActive) {
	super(entity, startActive);
    }

    @Override
    protected void collisionResponse(final CollisionComponent other) {

	if(isActive()){

	    getEntity().destroy();
	}
    }
}
