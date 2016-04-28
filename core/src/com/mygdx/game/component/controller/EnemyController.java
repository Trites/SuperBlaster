package com.mygdx.game.component.controller;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.Transform;

/**
 * Base for hostile AIControllers.
 * Will get a reference to the Entity with the given tag which can then be used by any derived classes.
 */
public abstract class EnemyController extends Controller
{
    private String targetTag;
    protected Transform target = null;

    protected EnemyController(final Entity entity, final String targetTag) {
	super(entity);

	this.targetTag = targetTag;
    }

    @Override
    public void start() {
	super.start();
	target = findEntity(targetTag).get(0).getTransform();
    }
}
